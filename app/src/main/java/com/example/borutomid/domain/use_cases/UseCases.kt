package com.example.borutomid.domain.use_cases

import com.example.borutomid.data.paging_source.SearchHeroSource
import com.example.borutomid.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.borutomid.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.borutomid.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.borutomid.domain.use_cases.search_heroes.SearchHeroUseCase

data class UseCases(
        val saveOnBoardingUseCase: SaveOnBoardingUseCase,
        val readOnBoardingUseCase: ReadOnBoardingUseCase,
        val getAllHeroesUseCase:GetAllHeroesUseCase,
        val searchHeroUseCase: SearchHeroUseCase
)