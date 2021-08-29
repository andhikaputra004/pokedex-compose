package com.example.pokedex.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonInfoEntity(
        @PrimaryKey
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "url")
        val url: String?,
        @ColumnInfo(name = "type")
        val type: String?
){
        fun getImageUrl(): String {
                val index = url?.split("/".toRegex())?.dropLast(1)?.last()

                return "https://assets.pokemon.com/assets/cms2/img/pokedex/full/${if (index?.toInt()!! < 10) ("00$index") else "0$index"}.png"

        }
}
