package cz.kotu.demo.azproducts.productdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import cz.kotu.demo.azproducts.loading.LoadingState
import cz.kotu.demo.azproducts.loading.LoadingStateContainer
import cz.kotu.demo.azproducts.ui.theme.AZProductsTheme

@Composable
fun ProductDetailContent(viewModel: ProductDetailViewModel) {
    val productsState: LoadingState<ProductDetail> by viewModel.product.collectAsState(
        LoadingState.Loading()
    )

    LoadingStateContainer(productsState) {
        ProductDetailContent(it)
    }
}

@Composable
fun ProductDetailContent(product: ProductDetail) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Image(
            painter = rememberImagePainter(product.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )

        Text(
            text = product.name,
            style = MaterialTheme.typography.h5,
        )

        Text(
            text = product.description,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailContentPreview() {
    AZProductsTheme {
        ProductDetailContent(
            ProductDetail(
                1,
                "Mimozemšťan",
                description = "Přivítejte tohoto roztomilého návštěvníka z jiné galaxie také u vás doma. Uvidíte, kolik zábavy s ním zažijete a jaké legrační kousky vám předvede. A co teprve jeho populární hlášky!",
                imageUrl = "can not load in preview",
            )
        )
    }
}