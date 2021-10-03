package cz.kotu.demo.azproducts.webservice

import retrofit2.http.GET

interface AzRetrofitService {
    @GET("v1/floors")
    suspend fun getFloors(): FloorsResponse

    data class FloorsResponse(val data: List<Category>)

    data class Category(val name: String)
}