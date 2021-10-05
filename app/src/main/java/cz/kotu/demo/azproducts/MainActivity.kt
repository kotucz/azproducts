package cz.kotu.demo.azproducts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.kotu.demo.azproducts.categories.CategoriesListContent
import cz.kotu.demo.azproducts.categories.CategoriesViewModel
import cz.kotu.demo.azproducts.categories.MockCategoryRepository
import cz.kotu.demo.azproducts.ui.theme.AZProductsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val categoriesViewModel: CategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AZProductsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CategoriesListContent(categoriesViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AZProductsTheme {
        CategoriesListContent(CategoriesViewModel(MockCategoryRepository()))
    }
}