package cz.kotu.demo.azproducts.categories

import kotlinx.coroutines.flow.Flow
import java.lang.Exception

interface CategoryRepository {
    val categoriesFlow: Flow<CategoriesState>
}

sealed class CategoriesState {
    class Loading: CategoriesState()
    class Data(val categories: List<Category>): CategoriesState()
    data class Error(val exception: Exception): CategoriesState()
}