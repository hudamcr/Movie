package com.huda.movie.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.huda.movie.data.model.MovieDetails
import com.huda.movie.data.model.Trailers
import com.huda.movie.data.model.TrailersList
import com.huda.movie.presentation.uistate.HomeEvents
import com.huda.movie.presentation.uistate.MovieDetailsEvents
import com.huda.movie.presentation.uistate.TrailersEvents
import com.huda.movie.presentation.viewmodel.MovieViewModel
import com.huda.movie.ui.theme.Purple200

@Composable
fun MovieDetailsScreen(movieId: Int, navController: NavController) {
    val viewModel: MovieViewModel = hiltViewModel()

    val movieDetailsState by viewModel.movieDetails.observeAsState()
    val trailersState by viewModel.trailers.observeAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getMovieDetails(movieId)
        viewModel.getMovieTrailers(movieId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val currentState = movieDetailsState
        when (currentState) {
            is MovieDetailsEvents.Success -> {
                val movieDetails = currentState.results
                MovieDetailsItemScreen(movieDetails, trailersState, navController)
            }
            is MovieDetailsEvents.Empty -> {
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
                            viewModel.getMovieDetails(movieId)
                            viewModel.getMovieTrailers(movieId)
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
fun MovieDetailsItemScreen(movieDetails: MovieDetails, trailersState: TrailersEvents?, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500${movieDetails.posterPath}")
                    .crossfade(true)
                    .build(),
                contentDescription = movieDetails.title,
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
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                text = movieDetails.originalTitle,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Text(
                text = "Vote Average: ${movieDetails.voteAverage}",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Text(
                text = "Original Language: ${movieDetails.originalLanguage}",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                text = "Overview",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Text(
                text = movieDetails.overview,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Text(
                text = "Trailers",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }

        item {
            val currentState = trailersState
            when (currentState) {
                is TrailersEvents.Success -> {
                    val trailers = currentState.results
                    TrailerGrid(trailers)
                }
                is TrailersEvents.Empty -> {
                }
                else -> {
                }
            }
        }

        item {
            Button(
                onClick = {
                    navController.navigate("movieDetails/${movieDetails.id}/review")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 50.dp)
                    .background(Purple200)
            ){
                Text(
                    text = "Reviews",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun TrailerGrid(trailers: Trailers) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        content = {
            items(trailers.results.size) { i ->
                TrailerGridItem(trailers.results[i])
            }
        }
    )
}

@Composable
fun TrailerGridItem(trailers: TrailersList) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
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
                        .data("https://img.youtube.com/vi/${trailers.key}/0.jpg")
                        .crossfade(true)
                        .build(),
                    contentDescription = trailers.key,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable{
                            val videoUrl = "https://www.youtube.com/watch?v=${trailers.key}"
                            uriHandler.openUri(videoUrl)
                        }
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
        }
    }
}