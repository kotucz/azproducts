package cz.kotu.demo.azproducts.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Category::class,
    ], version = 1
)
abstract class AzDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}