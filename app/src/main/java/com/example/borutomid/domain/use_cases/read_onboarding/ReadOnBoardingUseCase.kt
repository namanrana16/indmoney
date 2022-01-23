package com.example.borutomid.domain.use_cases.read_onboarding

import com.example.borutomid.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(private val repository: Repository) {

    operator fun invoke(): Flow<Boolean>
    {
        return repository.readOnBoardingState()
    }
}