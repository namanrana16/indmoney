package com.example.borutomid.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.borutomid.data.local.BorutoDatabase
import com.example.borutomid.data.remote.BorutoApi
import com.example.borutomid.domain.model.Hero
import com.example.borutomid.domain.model.HeroRemoteKeys
import java.lang.Exception
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteMediator<Int,Hero>() {

    private val heroDao = borutoDatabase.heroDao()
    private val heroRemoteKeysDao=borutoDatabase.heroRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {

        return try {

            val page = when(loadType){
                LoadType.REFRESH->{
                    val remoteKeys =heroRemoteKeyClosestoCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1)?:1
                }
                LoadType.APPEND->{
                    val remoteKeys = heroRemoteKeyForLastPosition(state)
                    val nextPage = remoteKeys?.nextPage?:
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)

                    nextPage
                }
                LoadType.PREPEND->{
                    val remoteKeys = heroRemoteKeyForFirstPosition(state)
                    val prevPage=remoteKeys?.prevPage?:
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)
                    prevPage
                }
            }

            val response= borutoApi.getAllHeroes(page =page )//CHeck this shit before
            if(response.heroes.isNotEmpty())
            {
                borutoDatabase.withTransaction {
                    if(loadType==LoadType.REFRESH)
                    {
                        heroDao.deleteAllHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()

                    }

                    val prevPage=response.prevPage
                    val nextPage=response.nextPage
                    val keys = response.heroes.map {
                        HeroRemoteKeys(id = it.id, prevPage = prevPage, nextPage = nextPage, lastUpdated = response.lastUpdated)
                    }
                    heroRemoteKeysDao.addAllRemoteKey(keys)
                    heroDao.addHeroes(response.heroes)
                }


            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage==null)
        }
        catch (e:Exception)
        {
            return MediatorResult.Error(e)
        }
    }




    override suspend fun initialize(): InitializeAction {

    val currentTime=System.currentTimeMillis()
        val lastUpdated=heroRemoteKeysDao.getRemoteKeys(1)?.lastUpdated?:0L
        val cacheTimeout=5
        val diffInMinutes=(currentTime-lastUpdated)/60000

        return  if(diffInMinutes.toInt()<=cacheTimeout)
        {
            InitializeAction.SKIP_INITIAL_REFRESH
        }else{
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }

    }




    private suspend fun heroRemoteKeyForLastPosition(state: PagingState<Int, Hero>): HeroRemoteKeys?{

        return state.pages.lastOrNull{
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { hero->
            heroRemoteKeysDao.getRemoteKeys(hero.id)
        }
    }

    private suspend fun heroRemoteKeyForFirstPosition(state: PagingState<Int, Hero>): HeroRemoteKeys? {

        return state.pages.firstOrNull{
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { hero->
            heroRemoteKeysDao.getRemoteKeys(hero.id)
        }

    }

    private suspend fun heroRemoteKeyClosestoCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKeys? {

        return state.anchorPosition?.let { position->
            state.closestItemToPosition(position)?.id?.let { id->
                heroRemoteKeysDao.getRemoteKeys(id)
            }

        }


    }


}