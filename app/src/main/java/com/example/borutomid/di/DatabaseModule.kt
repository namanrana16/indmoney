package com.example.borutomid.di

import android.content.Context
import androidx.room.Room
import com.example.borutomid.data.local.BorutoDatabase
import com.example.borutomid.data.repository.LocalDataSourceImpl
import com.example.borutomid.domain.repository.LocalDataSource
import com.example.borutomid.util.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    )=Room.databaseBuilder(
            context,BorutoDatabase::class.java,
                BORUTO_DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(database:BorutoDatabase):LocalDataSource
    {

         return LocalDataSourceImpl(borutoDatabase = database )
    }
}