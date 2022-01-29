package com.example.borutomid.domain.repository

import androidx.paging.PagingData
import com.example.borutomid.domain.model.Hero
import java.util.concurrent.Flow

interface RemoteDataSource
{
    fun getAllHeroes():kotlinx.coroutines.flow.Flow<PagingData<Hero>>
    fun searchHeroes(query:String):kotlinx.coroutines.flow.Flow<PagingData<Hero>>

}