package com.example.borutomid.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.borutomid.data.local.BorutoDatabase
import com.example.borutomid.data.paging_source.HeroRemoteMediator
import com.example.borutomid.data.remote.BorutoApi
import com.example.borutomid.domain.model.Hero
import com.example.borutomid.domain.repository.RemoteDataSource
import com.example.borutomid.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow


@ExperimentalPagingApi
class RemoteDataSourceImpl(private val borutoDatabase: BorutoDatabase,
                           private val borutoApi: BorutoApi) : RemoteDataSource {

    private val heroDao= borutoDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {

        val pagingSourceFactory={
            heroDao.getAllHeroes()
        }
        return Pager(config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(borutoApi = borutoApi, borutoDatabase = borutoDatabase),
        pagingSourceFactory = pagingSourceFactory).flow
    }

    override fun searchAllData(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }


}