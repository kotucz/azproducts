package cz.kotu.demo.azproducts.categories

object CategoryRepository {
    fun getCategories() = listOf<Category>(
        Category("Vidlíčky"),
        Category("Nože"),
        Category("Lžičky"),
    )
}