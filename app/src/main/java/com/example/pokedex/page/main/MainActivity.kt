package com.example.pokedex.page.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.pokedex.data.model.Pokemon
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val pokemons = viewModel.pokemon.value
    Scaffold(topBar = { MainAppBar() }, backgroundColor = Color.DarkGray) {
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(pokemons.size) { pokemon ->
                PokemonItem(pokemon = pokemons[pokemon])
            }
        }
    }
}

@Composable
fun MainAppBar() {
    TopAppBar(backgroundColor = Color.Red, contentColor = Color.Black) {
        Text(text = "Pokedex", fontSize = 16.sp)
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon) {
    val painter =
        rememberImagePainter(pokemon.getImageUrl())
    Card(
        modifier = Modifier
            .padding(6.dp)
            .wrapContentSize()
    ) {
        Column {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(12.dp)
            )
            Text(text = pokemon.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                ,
                textAlign = TextAlign.Start,
                fontSize = 18.sp)
        }
    }
}

@Preview(name = "light mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
//    MainScreen()
}