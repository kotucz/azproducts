package cz.kotu.demo.azproducts.categories

import cz.kotu.demo.azproducts.webservice.AzRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val azService: AzRetrofitService,
) : CategoryRepository {
    override val categoriesFlow: Flow<List<Category>> = flow {
        emit(azService.getFloors().data.map {
            Category(title = it.name)
        })
    }
}