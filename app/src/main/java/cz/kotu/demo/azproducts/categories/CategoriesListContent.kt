package cz.kotu.demo.azproducts.categories

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.kotu.demo.azproducts.loading.LoadingState
import cz.kotu.demo.azproducts.loading.LoadingStateContainer
import cz.kotu.demo.azproducts.ui.theme.AZProductsTheme

@Composable
fun CategoriesListContent(
    categoriesViewModel: CategoriesViewModel,
    onCategoryClick: (Category) -> Unit,
) {
    val categoriesState: LoadingState<List<Category>> by categoriesViewModel.categories.collectAsState(
        LoadingState.Loading()
    )

    LoadingStateContainer(categoriesState) { categories: List<Category> ->
        CategoriesList(categories, onCategoryClick)
    }
}

@Composable
private fun CategoriesList(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(
            items = categories,
            itemContent = { category ->
                CategoryListItem(category, onClick = { onCategoryClick(category) })
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesListPreview() {
    AZProductsTheme {
        CategoriesList(listOf(
            Category(1, "Alfa"),
            Category(2, "Bravo"),
            Category(3, "Charlie"),
            Category(4, "Delta"),
            Category(5, "Foxtrot"),
            Category(6, "Uniform"),
            Category(7, "Charlie"),
            Category(8, "Kilo"),
        ), onCategoryClick = {})
    }
}

@Composable
fun CategoryListItem(
    category: Category, onClick: () -> Unit
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        color = Color.LightGray,
    ) {
        Text(
            text = category.title, style = typography.h4,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListItemPreview() {
    AZProductsTheme {
        CategoryListItem(Category(1, "Ponožky"), onClick = {})
    }
}