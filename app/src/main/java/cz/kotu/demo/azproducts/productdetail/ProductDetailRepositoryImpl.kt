package cz.kotu.demo.azproducts.productdetail

import cz.kotu.demo.azproducts.database.DbCache
import cz.kotu.demo.azproducts.database.ProductDetailDao
import cz.kotu.demo.azproducts.loading.LoadingState
import cz.kotu.demo.azproducts.loading.LoadingState.*
import cz.kotu.demo.azproducts.webservice.AzRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import cz.kotu.demo.azproducts.database.ProductDetail as ProductDetailDb

class ProductDetailRepositoryImpl @Inject constructor(
    private val azService: AzRetrofitService,
    private val dbCache: DbCache,
    private val productDetailDao: ProductDetailDao,
) : ProductDetailRepository {
    override fun productFlow(productId: Long): Flow<LoadingState<ProductDetail>> = flow {
        emit(Loading())
        try {
            val product = dbCache.cachedOrGet(
                "product/$productId",
                download = { getProduct(productId) },
                storeDb = { productDetailDao.insert(it.appToDb()) },
                loadDb = { productDetailDao.getById(productId).dbToApp() },
            )
            emit(Data(product))
        } catch (e: Exception) {
            emit(Error(e))
        }
    }

    private fun ProductDetail.appToDb(): ProductDetailDb =
        ProductDetailDb(id = id, name = name, description = description, imageUrl = imageUrl)

    private fun ProductDetailDb.dbToApp() =
        ProductDetail(id = id, name = name, description = description, imageUrl = imageUrl)

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