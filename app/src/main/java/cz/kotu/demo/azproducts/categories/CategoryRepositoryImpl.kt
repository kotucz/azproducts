package cz.kotu.demo.azproducts.categories

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
    private val categoryDao: CategoryDao,
) : CategoryRepository {
    override val categoriesFlow: Flow<LoadingState<List<Category>>> = flow {
        emit(Loading())
        try {
            val cached = categoryDao.getAll()
                .map { it.dbToApp() }

            val categories: List<Category> = if (cached.isEmpty()) {
                getCategories().also { loaded ->
                    categoryDao.insertAll(loaded.map {
                        it.appToDb()
                    })
                }
            } else {
                cached
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