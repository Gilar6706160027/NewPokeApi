package com.gilarajip.mypekoapi.model.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.gilarajip.mypekoapi.model.local.PokemonDatabase
import com.gilarajip.mypekoapi.model.network.ApiService
import com.gilarajip.mypekoapi.model.network.PokemonData

class PokemonRepository(
    private val pokemonDatabase: PokemonDatabase,
    private val apiService: ApiService
) {
    fun getPokemon(name: String): LiveData<PagingData<PokemonData>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            remoteMediator = PokemonRemoteMediator(
                name,
                pokemonDatabase,
                apiService
            ),
            pagingSourceFactory = {
                pokemonDatabase.pokemonDao().getAllPokemon()
            }
        ).liveData
    }

    suspend fun deleteAllPokemon() {
        pokemonDatabase.pokemonDao().deleteAll()
        pokemonDatabase.remoteKeysDao().deleteRemoteKeys()
    }

    suspend fun isPokemonEmpty(): LiveData<Boolean> = pokemonDatabase.pokemonDao().isPokemonEmpty()
}