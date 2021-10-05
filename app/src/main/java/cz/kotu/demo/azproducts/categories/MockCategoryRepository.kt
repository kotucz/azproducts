package cz.kotu.demo.azproducts.categories

import cz.kotu.demo.azproducts.loading.LoadingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MockCategoryRepository @Inject constructor() : CategoryRepository {
    override val categoriesFlow: Flow<LoadingState<List<Category>>> = flowOf(
        LoadingState.Data(listOf<Category>(
            Category("Vidličky"),
            Category("Nože"),
            Category("Lžičky"),
        ))
    )
}