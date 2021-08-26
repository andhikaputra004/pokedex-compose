package com.example.pokedex.utils

import androidx.compose.ui.graphics.Color
import com.example.pokedex.ui.theme.*

object PokemonTypeUtils {

    fun getTypeColor(type: String): Color {
        return when (type) {
            "fighting" -> Fighter
            "flying" -> Flying
            "poison" -> Poison
            "ground" -> Ground
            "rock" -> Rock
            "bug" -> Bug
            "ghost" -> Ghost
            "steel" -> Steel
            "fire" -> Fire
            "water" -> Water
            "grass" -> Grass
            "electric" -> Electric
            "psychic" -> Psychic
            "ice" -> Ice
            "dragon" -> Dragon
            "fairy" -> Fairy
            "dark" -> Dark
            else -> Gray21
        }
    }
}