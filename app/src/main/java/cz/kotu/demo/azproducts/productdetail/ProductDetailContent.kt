package cz.kotu.demo.azproducts.productdetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.kotu.demo.azproducts.ui.theme.AZProductsTheme

@Composable
fun ProductDetailContent(viewModel: ProductDetailViewModel) {
    Text(text = "Product ${viewModel.productId}")
}

@Preview(showBackground = true)
@Composable
fun ProductDetailContentPreview() {
    AZProductsTheme {
        ProductDetailContent(ProductDetailViewModel(1))
    }
}