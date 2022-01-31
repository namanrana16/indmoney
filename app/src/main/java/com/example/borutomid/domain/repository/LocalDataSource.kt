package com.example.borutomid.domain.repository

import com.example.borutomid.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId:Int):Hero
}