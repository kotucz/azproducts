package cz.kotu.demo.azproducts.di

import cz.kotu.demo.azproducts.categories.CategoryRepository
import cz.kotu.demo.azproducts.categories.CategoryRepositoryImpl
import cz.kotu.demo.azproducts.productdetail.ProductDetailRepository
import cz.kotu.demo.azproducts.productdetail.ProductDetailRepositoryImpl
import cz.kotu.demo.azproducts.products.ProductsRepository
import cz.kotu.demo.azproducts.products.ProductsRepositoryImpl
import cz.kotu.demo.azproducts.webservice.AzRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AzModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.alza.cz/Services/RestService.svc/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideAzRetrofitService(
        retrofit: Retrofit
    ): AzRetrofitService = retrofit.create(AzRetrofitService::class.java)

    @Provides
    fun provideCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository = impl

    @Provides
    fun provideProductRepository(
        impl: ProductsRepositoryImpl
    ): ProductsRepository = impl

    @Provides
    fun provideProductDetailRepository(
        impl: ProductDetailRepositoryImpl
    ): ProductDetailRepository = impl
}