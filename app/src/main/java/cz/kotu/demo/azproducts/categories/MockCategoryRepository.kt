package cz.kotu.demo.azproducts.categories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MockCategoryRepository @Inject constructor() : CategoryRepository {
    override val categoriesFlow: Flow<List<Category>> = flowOf(
        listOf<Category>(
            Category("Vidličky"),
            Category("Nože"),
            Category("Lžičky"),
        )
    )
}