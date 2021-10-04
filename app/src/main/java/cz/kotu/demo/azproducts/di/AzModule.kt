package cz.kotu.demo.azproducts.di

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

}