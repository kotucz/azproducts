package cz.kotu.demo.azproducts.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.kotu.demo.azproducts.loading.LoadingState
import cz.kotu.demo.azproducts.loading.LoadingStateContainer

@Composable
fun CategoriesListContent(
    categoriesViewModel: CategoriesViewModel,
    onCategoryClick: (Category) -> Unit,
) {
    val categoriesState: LoadingState<List<Category>> by categoriesViewModel.categories.collectAsState(
        LoadingState.Loading()
    )

    LoadingStateContainer(categoriesState) { categories: List<Category> ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = categories,
                itemContent = { category ->
                    CategoryListItem(category, onClick = { onCategoryClick(category) })
                }
            )
        }
    }
}

@Composable
fun CategoryListItem(
    category: Category, onClick: () -> Unit
) {
    Column {
        Text(
            text = category.title, style = typography.h6,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}
