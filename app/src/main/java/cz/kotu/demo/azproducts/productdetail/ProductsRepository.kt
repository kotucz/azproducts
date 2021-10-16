package cz.kotu.demo.azproducts.productdetail

import cz.kotu.demo.azproducts.loading.LoadingState
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {
    fun productFlow(productId: Long): Flow<LoadingState<ProductDetail>>
}