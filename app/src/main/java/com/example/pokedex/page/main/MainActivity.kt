package com.example.pokedex.page.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.pokedex.data.local.PokemonInfoEntity
import com.example.pokedex.utils.PokemonTypeUtils
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
    val pokemons by viewModel.pokemon.observeAsState()
    Scaffold(topBar = { MainAppBar() }, backgroundColor = Color.DarkGray) {
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(pokemons?.size ?: 0) { pokemon ->
                PokemonItem(pokemon = pokemons?.get(pokemon))
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
fun PokemonItem(pokemon: PokemonInfoEntity?) {
    val painter =
        rememberImagePainter(pokemon?.getImageUrl())
    Card(
        modifier = Modifier
            .padding(6.dp)
            .wrapContentSize()
    ) {
        Column(modifier = Modifier.background(PokemonTypeUtils.getTypeColor(pokemon?.type ?: ""))) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(12.dp)
            )
            Text(text = pokemon?.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,color = Color.White)
        }
    }
}

@Preview(name = "light mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
//    MainScreen()
}