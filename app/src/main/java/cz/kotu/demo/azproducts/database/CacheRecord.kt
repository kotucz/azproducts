package cz.kotu.demo.azproducts.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Entity
data class CacheRecord(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "time") val time: Long,
)

@Dao
interface CacheRecordDao {
    @Insert(onConflict = REPLACE)
    suspend fun put(record: CacheRecord)

    @Query("SELECT * FROM cacheRecord WHERE name = :name")
    suspend fun get(name: String): CacheRecord?
}