package cz.kotu.demo.azproducts.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CacheRecord::class,
        Category::class,
        Product::class,
    ], version = 1
)
abstract class AzDatabase : RoomDatabase() {
    abstract fun cacheRecordDao(): CacheRecordDao
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}