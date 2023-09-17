package com.huda.movie.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.huda.movie.data.model.GenreList
import com.huda.movie.presentation.uistate.GenreEvents
import com.huda.movie.presentation.uistate.HomeEvents
import com.huda.movie.presentation.viewmodel.MovieViewModel

@Composable
fun GenreScreen(paddingModifier: Modifier, navController: NavController) {
    val viewModel: MovieViewModel = hiltViewModel()

    val genreState by viewModel.genres.observeAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getGenres()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val currentState = genreState
        when (currentState) {
            is GenreEvents.Success -> {
                val genres = currentState.results
                if(genres.results.isNotEmpty()){
                    GenreList(genres.results, paddingModifier, navController)
                }
                else{
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            text = "Tidak ada data",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            is GenreEvents.Empty -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        text = "Tidak ada data",
                        modifier = Modifier.padding(8.dp)
                    )
                    Button(
                        onClick = {
                            viewModel.getGenres()
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Refresh")
                    }
                }
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun GenreList(genres: List<GenreList>, paddingModifier: Modifier, navController: NavController) {
    LazyColumn(
        paddingModifier
    ) {
        items(genres.size) { i ->
            GenreListItem(
                id = genres[i].id,
                name = genres[i].name,
                icon = Icons.Default.ArrowForward,
                navController = navController
            )
        }
    }
}

@Composable
fun GenreListItem(id: Int, name: String, icon: ImageVector, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("movieScreen/${id}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}