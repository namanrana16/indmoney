package com.example.borutomid.domain.use_cases.save_onboarding

import com.example.borutomid.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(completed:Boolean)
    {

        repository.savedOnBoardingState(completed = completed)
    }
}