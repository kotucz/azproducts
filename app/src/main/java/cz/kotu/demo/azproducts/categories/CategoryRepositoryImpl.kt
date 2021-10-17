package cz.kotu.demo.azproducts.categories

import android.text.format.DateUtils.MINUTE_IN_MILLIS
import androidx.room.withTransaction
import cz.kotu.demo.azproducts.database.AzDatabase
import cz.kotu.demo.azproducts.database.CacheRecord
import cz.kotu.demo.azproducts.database.CacheRecordDao
import cz.kotu.demo.azproducts.database.CategoryDao
import cz.kotu.demo.azproducts.loading.LoadingState
import cz.kotu.demo.azproducts.loading.LoadingState.*
import cz.kotu.demo.azproducts.webservice.AzRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import cz.kotu.demo.azproducts.database.Category as CategoryDb

class CategoryRepositoryImpl @Inject constructor(
    private val azService: AzRetrofitService,
    private val cacheRecordDao: CacheRecordDao,
    private val categoryDao: CategoryDao,
    private val db: AzDatabase,
) : CategoryRepository {
    override val categoriesFlow: Flow<LoadingState<List<Category>>> = flow {
        emit(Loading())
        try {
            val cacheKey = "categories"

            val currentTime = System.currentTimeMillis()
            val cacheRecord = cacheRecordDao.get(cacheKey)
            val cacheTime = cacheRecord?.time ?: 0

            val categories: List<Category> = if (cacheTime + MINUTE_IN_MILLIS < currentTime) {
                getCategories().also { loaded ->
                    db.withTransaction {
                        categoryDao.replaceAll(loaded.map {
                            it.appToDb()
                        })
                        cacheRecordDao.put(CacheRecord(cacheKey, currentTime))
                    }
                }
            } else {
                categoryDao.getAll()
                    .map { it.dbToApp() }
            }

            emit(Data(categories))
        } catch (e: Exception) {
            emit(Error(e))
        }
    }

    private fun Category.appToDb() = CategoryDb(id = id, name = title)

    private fun CategoryDb.dbToApp() = Category(id, name)

    private suspend fun getCategories() = azService.getFloors().data.map {
        Category(
            id = it.id,
            title = it.name
        )
    }
}