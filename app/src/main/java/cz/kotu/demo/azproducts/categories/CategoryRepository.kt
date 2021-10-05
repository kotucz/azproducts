package cz.kotu.demo.azproducts.categories

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    val categoriesFlow: Flow<List<Category>>
}