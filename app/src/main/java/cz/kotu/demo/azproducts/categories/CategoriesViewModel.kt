package cz.kotu.demo.azproducts.categories

import androidx.lifecycle.ViewModel
import cz.kotu.demo.azproducts.loading.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    val categories: Flow<LoadingState<List<Category>>> = categoryRepository.categoriesFlow
}