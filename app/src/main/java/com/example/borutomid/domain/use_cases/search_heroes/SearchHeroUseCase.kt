package com.example.borutomid.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.example.borutomid.data.repository.Repository
import com.example.borutomid.domain.model.Hero
import retrofit2.http.Query
import java.util.concurrent.Flow

class SearchHeroUseCase(private val repository: Repository) {

    operator fun invoke(query: String):kotlinx.coroutines.flow.Flow<PagingData<Hero>>
    {
        return repository.searchHeroes(query = query)
    }
}