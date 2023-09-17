package com.huda.movie.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.huda.movie.data.model.MovieList
import com.huda.movie.presentation.uistate.HomeEvents
import com.huda.movie.presentation.uistate.MovieEvents
import com.huda.movie.presentation.viewmodel.MovieViewModel
import com.huda.movie.ui.theme.Purple200

@Composable
fun MovieScreen(genreId: Int, paddingModifier: Modifier, navController: NavHostController) {
    val viewModel: MovieViewModel = hiltViewModel()
    val page = 1
    val movieState by viewModel.movies.observeAsState()

    LaunchedEffect(key1 = Unit) {
        Log.d("log__","$genreId")
        viewModel.getMovies(genreId, page)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val currentState = movieState
        when (currentState) {
            is MovieEvents.Success -> {
                val movies = currentState.results
                if(movies.results.isNotEmpty()){
                    MovieGrid(movies.results, paddingModifier, navController)
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
            is MovieEvents.Empty -> {
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
                            viewModel.getMovies(genreId, page)
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
fun MovieGrid(movies: List<MovieList>, paddingModifier: Modifier, navController: NavController) {
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = paddingModifier
    ) {
        items(movies.size) { i ->
            MovieGridItem(movie = movies[i], navController)
        }
    }
}

@Composable
fun MovieGridItem(movie: MovieList, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple200)
                .clickable {
                    navController.navigate("movieDetails/${movie.id}")
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                        .crossfade(true)
                        .build(),
                    contentDescription = movie.title,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(
                                topStart = 5.dp,
                                topEnd = 5.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Purple200)
                    .padding(8.dp)
                    .heightIn(min = 48.dp)
            ) {
                Text(
                    text = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Purple200)
                    .padding(8.dp)
                    .heightIn(min = 48.dp)
            ) {
                Text(
                    text = "Vote Average: ${movie.voteAverage}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}