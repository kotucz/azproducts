package cz.kotu.demo.azproducts.webservice

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AzRetrofitService {
    @GET("v1/floors")
    suspend fun getFloors(): FloorsResponse

    data class FloorsResponse(val data: List<Category>)

    data class Category(
        val id: Long,
        val name: String,
    )

    @POST("v2/products")
    suspend fun getProducts(
        @Body filter: ProductsFilter
    ): ProductsResponse

    data class ProductsFilter(val filterParameters: FilterParameters)

    data class FilterParameters(
        /** Category.id */
        val id: Long
    )

    data class ProductsResponse(val data: List<Product>)

    data class Product(
        val id: Long,
        val name: String
    )
}