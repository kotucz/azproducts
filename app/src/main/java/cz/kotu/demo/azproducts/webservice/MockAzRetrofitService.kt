package cz.kotu.demo.azproducts.webservice

import cz.kotu.demo.azproducts.webservice.AzRetrofitService.*
import kotlinx.coroutines.delay

class MockAzRetrofitService : AzRetrofitService {
    override suspend fun getFloors(): FloorsResponse {
        networkDelay()
        return FloorsResponse(
            data = "Jak mám pracovat, když mi server pořád hlásí HTTP 430. Asi tam maji anti-spamovou ochranu"
                .split(" ")
                .mapIndexed { i, n -> Category(i.toLong(), n) }
        )
    }

    override suspend fun getProducts(filter: ProductsFilter): ProductsResponse {
        networkDelay()
        val categoryId = filter.filterParameters.id
        return ProductsResponse(
            data = "Produkty pro kategorii číslo $categoryId"
                .split(" ")
                .mapIndexed { i, n -> Product(1000 * categoryId + i.toLong(), n) }
        )
    }

    override suspend fun getProductDetail(productId: Long): ProductDetailResponse {
        networkDelay()
        return ProductDetailResponse(
            data = ProductDetail(
                id = productId,
                img = "https://cdn.alza.cz/Foto/ImgGalery/Image/mluvici-plysovy-mimozemstan-alza-3_1.JPG",
                name = "Product $productId",
                spec = "Na Alze už jede výprodej, výprodej.",
            )
        )
    }

    private suspend fun networkDelay() {
        delay(5000)
    }
}