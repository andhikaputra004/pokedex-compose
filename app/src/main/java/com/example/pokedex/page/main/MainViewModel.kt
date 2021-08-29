package com.example.pokedex.page.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.local.PokemonInfoEntity
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.remote.PokedexRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokedexRepository) : ViewModel() {

    val pokemon = repository.getAllPokemon()

    val pokemonType = mutableStateOf(emptyList<List<Pokemon>>())

    fun fetchPokemonList() {
        viewModelScope.launch {
            val response = repository.fetchPokemonList()
            response.forEach {
                val poke = repository.fetchPokemonType(it.name ?: "")

                repository.insertPokemon(PokemonInfoEntity(it.name
                        ?: "", it.url, poke.types?.get(0)?.type?.name))
            }
        }
    }



    init {
        fetchPokemonList()
    }
}