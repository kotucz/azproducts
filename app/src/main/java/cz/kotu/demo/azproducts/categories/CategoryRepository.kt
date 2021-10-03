package cz.kotu.demo.azproducts.categories

import javax.inject.Inject

class CategoryRepository @Inject constructor() {
    fun getCategories() = listOf<Category>(
        Category("Vidličky"),
        Category("Nože"),
        Category("Lžičky"),
    )
}