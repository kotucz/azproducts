package cz.kotu.demo.azproducts.database

import androidx.room.*

@Entity(
    primaryKeys = ["id", "categoryId"]
)
data class Product(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "categoryId") val categoryId: Long,
    @ColumnInfo(name = "name") val name: String,
)

@Dao
interface ProductDao {
    suspend fun updateCategory(categoryId: Long, list: List<Product>) {
        deleteByCategory(categoryId)
        insertAll(list)
    }

    @Insert
    suspend fun insertAll(products: List<Product>)

    @Query("SELECT * FROM product WHERE categoryId = :categoryId")
    suspend fun getByCategory(categoryId: Long): List<Product>

    @Query("DELETE FROM Product WHERE categoryId = :categoryId")
    suspend fun deleteByCategory(categoryId: Long)
}