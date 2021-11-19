package com.example.moviemanager.Base.di

import android.content.Context
import androidx.room.Room
import com.example.moviemanager.Base.AppDatabase
import com.example.moviemanager.Repository.RetroInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    fun roomProvider(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movies"
        ).build()
    }

    @Provides
    fun retrofitProvider(): RetroInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(RetroInterface::class.java)
    }
}