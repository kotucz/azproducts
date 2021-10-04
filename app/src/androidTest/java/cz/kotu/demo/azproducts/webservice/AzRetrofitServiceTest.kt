package cz.kotu.demo.azproducts.webservice

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThan
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
}