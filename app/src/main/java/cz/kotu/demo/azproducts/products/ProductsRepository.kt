package cz.kotu.demo.azproducts.products

import cz.kotu.demo.azproducts.loading.LoadingState
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun productsFlow(categoryId: Long): Flow<LoadingState<List<Product>>>
}