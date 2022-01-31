package com.example.borutomid.data.repository

import com.example.borutomid.data.local.BorutoDatabase
import com.example.borutomid.domain.model.Hero
import com.example.borutomid.domain.repository.LocalDataSource

class LocalDataSourceImpl(borutoDatabase: BorutoDatabase): LocalDataSource {

    private val heroDao=borutoDatabase.heroDao()
    override suspend fun getSelectedHero(heroId: Int): Hero {

        return heroDao.getSelectedHero(heroId = heroId)

    }

}