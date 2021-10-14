package cz.kotu.demo.azproducts.webservice

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThan
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AzRetrofitServiceTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject lateinit var service: AzRetrofitService

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun getCategories() = runBlocking {
        val categories = service.getFloors().data

        assertThat(categories.size, greaterThan(1))
    }

    @Test
    fun getProducts() = runBlocking {
        val category = AzRetrofitService.Category(
            id = 18851104,
            name = "Hračky, pro děti a miminka",
        )

        val products = service.getProducts(
            AzRetrofitService.ProductsFilter(
                AzRetrofitService.FilterParameters(
                    id = category.id
                )
            )
        ).data

        assertThat(products.size, greaterThan(1))
    }

    @Test
    fun getProductDetail() = runBlocking {
        val productId: Long = 5632104
        val productDetail = service.getProductDetail(productId).data

        assertEquals(productId, productDetail.id)
    }
}