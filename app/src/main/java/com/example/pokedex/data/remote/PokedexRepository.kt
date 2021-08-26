package com.example.pokedex.data.remote

import com.example.pokedex.data.model.Pokemon

class PokedexRepository(private val service: PokedexService) {

    suspend fun fetchPokemonList(): List<Pokemon> {
        return service.fetchPokemonList().results ?: emptyList()
    }

    suspend fun fetchPokemonType(): List<Pokemon> {
        return service.fetchColorPokemonType().results ?: emptyList()
    }

}