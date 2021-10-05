package cz.kotu.demo.azproducts.categories

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _categories = MutableStateFlow(listOf<Category>())
    val categories: Flow<List<Category>> = _categories.asStateFlow()

    init {
        _categories.value = categoryRepository.getCategories()
    }

    fun duplicateCategory(category: Category) {
        _categories.value = _categories.value + category
    }
}