package cz.kotu.demo.azproducts.products

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
class ProductsActivity : ComponentActivity() {

    companion object {
        const val CATEGORY_ID = "CATEGORY_ID"

        fun newIntent(context: Context, categoryId: Long) =
            Intent(context, ProductsActivity::class.java)
                .putExtra(CATEGORY_ID, categoryId)
    }

    @Inject lateinit var viewModelAssistedFactory: ProductsViewModel.Factory

    private val productsViewModel: ProductsViewModel by viewModels {
        ProductsViewModel.provideFactory(
            viewModelAssistedFactory,
            intent.getLongExtra(CATEGORY_ID, 0)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AZProductsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ProductsListContent(productsViewModel)
                }
            }
        }
    }
}
