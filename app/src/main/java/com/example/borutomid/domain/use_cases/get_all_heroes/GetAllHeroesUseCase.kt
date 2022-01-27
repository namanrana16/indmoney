package com.example.borutomid.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.example.borutomid.data.repository.Repository
import com.example.borutomid.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(private val repository: Repository) {

    operator fun invoke():Flow<PagingData<Hero>>
    {
        return repository.getAllHeroes()
    }
}