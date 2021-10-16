package cz.kotu.demo.azproducts.products

import cz.kotu.demo.azproducts.loading.LoadingState
import cz.kotu.demo.azproducts.loading.LoadingState.*
import cz.kotu.demo.azproducts.webservice.AzRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val azService: AzRetrofitService,
) : ProductsRepository {
    override fun productsFlow(categoryId: Long): Flow<LoadingState<List<Product>>> = flow {
        emit(Loading())
        try {
            val products = getProducts(categoryId)
            emit(Data(products))
        } catch (e: Exception) {
            emit(Error(e))
        }
    }

    private suspend fun getProducts(categoryId: Long) = azService.getProducts(
        filter = AzRetrofitService.ProductsFilter(
            filterParameters = AzRetrofitService.FilterParameters(categoryId),
        ),
    ).data.map {
        Product(
            id = it.id,
            name = it.name,
        )
    }
}