package cz.kotu.demo.azproducts.products

import cz.kotu.demo.azproducts.database.DbCache
import cz.kotu.demo.azproducts.database.ProductDao
import cz.kotu.demo.azproducts.loading.LoadingState
import cz.kotu.demo.azproducts.loading.LoadingState.*
import cz.kotu.demo.azproducts.webservice.AzRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import cz.kotu.demo.azproducts.database.Product as ProductDb

class ProductsRepositoryImpl @Inject constructor(
    private val azService: AzRetrofitService,
    private val dbCache: DbCache,
    private val productDao: ProductDao,
) : ProductsRepository {
    override fun productsFlow(categoryId: Long): Flow<LoadingState<List<Product>>> = flow {
        emit(Loading())
        try {
            val products = dbCache.cachedOrGet(
                "category/$categoryId",
                download = { getProducts(categoryId) },
                storeDb = { d ->
                    productDao.updateCategory(
                        categoryId,
                        d.map { ProductDb(it.id, categoryId, it.name) })
                },
                loadDb = { productDao.getByCategory(categoryId).map { Product(it.id, it.name) } },
            )
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