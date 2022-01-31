package com.example.borutomid.domain.use_cases.get_selected_hero

import com.example.borutomid.data.repository.Repository
import com.example.borutomid.domain.model.Hero

class GetSelectedHeroUseCase(private val repository: Repository) {

    suspend operator fun invoke(heroId:Int):Hero
    {
        return repository.getSelectedHero(heroId = heroId )
    }
}