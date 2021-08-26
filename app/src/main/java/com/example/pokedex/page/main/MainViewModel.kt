package com.example.pokedex.page.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.remote.PokedexRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokedexRepository) : ViewModel() {

    val pokemon = mutableStateOf<List<Pokemon>>(emptyList())

    val pokemonType = mutableStateOf(emptyList<List<Pokemon>>())

    fun fetchPokemonList() {
        viewModelScope.launch {
            pokemon.value = repository.fetchPokemonList()
        }
    }

    fun fetchPokemonType() {
        viewModelScope.launch {

        }
    }

    init {
        fetchPokemonList()
    }
}