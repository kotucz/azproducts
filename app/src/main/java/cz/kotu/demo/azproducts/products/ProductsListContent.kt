package cz.kotu.demo.azproducts.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cz.kotu.demo.azproducts.loading.LoadingState
import cz.kotu.demo.azproducts.loading.LoadingStateContainer

@Composable
fun ProductsListContent(viewModel: ProductsViewModel, onProductClick: (Product) -> Unit) {
    val productsState: LoadingState<List<Product>> by viewModel.products.collectAsState(
        LoadingState.Loading()
    )

    LoadingStateContainer(productsState) { products: List<Product> ->
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = products,
                itemContent = { product ->
                    ProductListItem(product) { onProductClick(product) }
                }
            )
        }
    }
}

@Composable
fun ProductListItem(
    product: Product, onClick: () -> Unit
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        color = Color.LightGray,
    ) {
        Text(
            text = product.name, style = typography.h5,
            modifier = Modifier.padding(8.dp)
        )
    }
}
