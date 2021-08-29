package com.example.pokedex.data.remote

import com.example.pokedex.data.local.AppDatabase
import com.example.pokedex.data.local.PokemonInfoEntity
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.PokemonInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokedexRepository(private val service: PokedexService, private val appDatabase: AppDatabase) {

    private val pokemonDAO = appDatabase.pokemonDAO()

    suspend fun fetchPokemonList():List<Pokemon>{
        return service.fetchPokemonList().results ?: emptyList()
    }

    suspend fun fetchPokemonType(pokemonName: String): PokemonInfo {
        return service.fetchColorPokemonType(pokemonName)
    }

    suspend fun insertPokemon(pokemonList: PokemonInfoEntity) {
        pokemonDAO.insertPokemon(pokemonList)
    }

    fun getAllPokemon() = pokemonDAO.getAllPokemon()
}