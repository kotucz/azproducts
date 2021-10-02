package cz.kotu.demo.azproducts.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesListContent() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = CategoryRepository.getCategories(),
            itemContent = {
                CategoryListItem(it)
            }
        )
    }
}

@Composable
fun CategoryListItem(category: Category) {
    Column {
        Text(text = category.title, style = typography.h6)
    }
}
