package com.example.borutomid.data.repository

import androidx.paging.PagingData
import com.example.borutomid.domain.model.Hero
import com.example.borutomid.domain.repository.DataStoreOperations
import com.example.borutomid.domain.repository.LocalDataSource
import com.example.borutomid.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val dataStore:DataStoreOperations,
    private val remote:RemoteDataSource
) {


    suspend fun getSelectedHero(heroId:Int):Hero
    {

        return local.getSelectedHero(heroId = heroId)
    }


    fun getAllHeroes():Flow<PagingData<Hero>>
    {
        return remote.getAllHeroes()
    }


    fun searchHeroes(query:String):Flow<PagingData<Hero>>
    {

        return remote.searchHeroes(query = query)
    }

    suspend fun savedOnBoardingState(completed:Boolean){
        dataStore.saveOnBoardingState(completed = completed)

    }


fun readOnBoardingState(): Flow<Boolean>
{
    return dataStore.readOnBoardingState()
}

}