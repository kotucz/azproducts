package cz.kotu.demo.azproducts.categories

import cz.kotu.demo.azproducts.loading.LoadingState
import cz.kotu.demo.azproducts.loading.LoadingState.*
import cz.kotu.demo.azproducts.webservice.AzRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val azService: AzRetrofitService,
) : CategoryRepository {
    override val categoriesFlow: Flow<LoadingState<List<Category>>> = flow {
        emit(Loading())
        try {
            val categories = getCategories()
            emit(Data(categories))
        } catch (e: Exception) {
            emit(Error(e))
        }
    }

    private suspend fun getCategories() = azService.getFloors().data.map {
        Category(title = it.name)
    }
}