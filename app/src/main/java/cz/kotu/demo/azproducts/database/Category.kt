package cz.kotu.demo.azproducts.database

import androidx.room.*

@Entity
data class Category(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
)

@Dao
interface CategoryDao {
    @Insert
    suspend fun insertAll(categories: List<Category>)

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<Category>
}