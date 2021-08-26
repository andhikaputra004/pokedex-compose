package com.example.pokedex.data.remote

import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonResponse<Pokemon>

    @GET("pokemon-color")
    suspend fun fetchColorPokemonType(): PokemonResponse<Pokemon>
}