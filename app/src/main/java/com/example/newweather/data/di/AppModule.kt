package com.example.newweather.data.di

import com.example.newweather.BuildConfig.API_URL
import com.example.newweather.data.remote.ApiService
import com.example.newweather.data.repositoryImpl.WeatherRepositoryImpl
import com.example.newweather.domain.repo.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().apply {
            writeTimeout(20, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: ApiService): WeatherRepository {
        return WeatherRepositoryImpl(apiService)
    }
}