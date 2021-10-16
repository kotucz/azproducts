package cz.kotu.demo.azproducts.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import cz.kotu.demo.azproducts.ui.theme.AZProductsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailActivity : ComponentActivity() {
    companion object {
        private const val PRODUCT_ID = "PRODUCT_ID"

        fun newIntent(context: Context, categoryId: Long) =
            Intent(context, ProductDetailActivity::class.java)
                .putExtra(PRODUCT_ID, categoryId)
    }

    @Inject lateinit var viewModelAssistedFactory: ProductDetailViewModel.Factory

    private val productDetailViewModel: ProductDetailViewModel by viewModels {
        ProductDetailViewModel.provideFactory(
            viewModelAssistedFactory,
            intent.getLongExtra(PRODUCT_ID, 0)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AZProductsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ProductDetailContent(productDetailViewModel)
                }
            }
        }
    }
}
