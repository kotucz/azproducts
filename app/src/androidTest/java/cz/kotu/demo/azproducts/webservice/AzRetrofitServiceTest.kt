package cz.kotu.demo.azproducts.webservice

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThan
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(AndroidJUnit4::class)
class AzRetrofitServiceTest {
    @Test
    fun getCategories() = runBlocking {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.alza.cz/Services/RestService.svc/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service: AzRetrofitService = retrofit.create(AzRetrofitService::class.java)

        val categories = service.getFloors().data

        assertThat(categories.size, greaterThan(1))
    }
}