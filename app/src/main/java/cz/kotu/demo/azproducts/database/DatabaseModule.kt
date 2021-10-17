package cz.kotu.demo.azproducts.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun database(context: Application): AzDatabase = Room.databaseBuilder(
        context,
        AzDatabase::class.java,
        "az-products-cache"
    ).build()

    @Provides
    fun categoryDao(db: AzDatabase) = db.categoryDao()

    @Provides
    fun cacheRecordDao(db: AzDatabase) = db.cacheRecordDao()

    @Provides
    fun productDao(db: AzDatabase) = db.productDao()

    @Provides
    fun productDetailDao(db: AzDatabase) = db.productDetailDao()
}