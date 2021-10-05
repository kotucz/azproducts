package cz.kotu.demo.azproducts.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.kotu.demo.azproducts.ui.theme.ErrorRed

@Composable
fun CategoriesListContent(categoriesViewModel: CategoriesViewModel) {
    val categoriesState: CategoriesState by categoriesViewModel.categories.collectAsState(
        CategoriesState.Loading()
    )

    when (val state = categoriesState) {
        is CategoriesState.Loading -> {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator()
            }
        }
        is CategoriesState.Data -> {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = state.categories,
                    itemContent = { category ->
                        CategoryListItem(category) {
                            TODO("open category")
                        }
                    }
                )
            }
        }
        is CategoriesState.Error -> {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    color = ErrorRed,
                    text = state.exception.toString()
                )
            }
        }
    }
}

@Composable
fun CategoryListItem(
    category: Category, onCategoryClick: () -> Unit
) {
    Column {
        Text(
            text = category.title, style = typography.h6,
            modifier = Modifier.clickable(onClick = onCategoryClick)
        )
    }
}
