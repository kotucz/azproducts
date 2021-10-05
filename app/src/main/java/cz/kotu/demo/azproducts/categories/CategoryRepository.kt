package cz.kotu.demo.azproducts.categories

import cz.kotu.demo.azproducts.loading.LoadingState
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    val categoriesFlow: Flow<LoadingState<List<Category>>>
}