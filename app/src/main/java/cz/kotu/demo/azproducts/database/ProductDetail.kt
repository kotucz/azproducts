package cz.kotu.demo.azproducts.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Entity
data class ProductDetail(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
)

@Dao
interface ProductDetailDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(productDetail: ProductDetail)

    @Query("SELECT * FROM ProductDetail WHERE id = :productId")
    suspend fun getById(productId: Long): ProductDetail
}