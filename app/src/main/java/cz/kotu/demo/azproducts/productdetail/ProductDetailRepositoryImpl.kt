package cz.kotu.demo.azproducts.productdetail

import cz.kotu.demo.azproducts.loading.LoadingState
import cz.kotu.demo.azproducts.loading.LoadingState.*
import cz.kotu.demo.azproducts.webservice.AzRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val azService: AzRetrofitService,
) : ProductDetailRepository {
    override fun productFlow(productId: Long): Flow<LoadingState<ProductDetail>> = flow {
        emit(Loading())
        try {
            val products = getProduct(productId)
            emit(Data(products))
        } catch (e: Exception) {
            emit(Error(e))
        }
    }

    private suspend fun getProduct(productId: Long) =
        azService.getProductDetail(productId).data.let {
            ProductDetail(
                id = it.id,
                name = it.name,
                description = it.spec,
                imageUrl = it.img,
            )
        }
}