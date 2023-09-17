package com.huda.movie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huda.movie.data.model.ReviewsList
import com.huda.movie.presentation.uistate.ReviewsEvents
import com.huda.movie.presentation.viewmodel.MovieViewModel

@Composable
fun ReviewsScreen(movieId: Int, paddingModifier: Modifier) {
    val viewModel: MovieViewModel = hiltViewModel()
    val page = 1
    val reviewState by viewModel.reviews.observeAsState()

    LaunchedEffect(key1 = Unit)
    {
        viewModel.getReviews(movieId, page)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        val currentState = reviewState
        when (currentState) {
            is ReviewsEvents.Success -> {
                val reviews = currentState.results
                if (reviews.results.isNotEmpty()) {
                    ReviewList(reviews.results, paddingModifier)
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            text = "Tidak ada review",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            is ReviewsEvents.Empty -> {
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
                            viewModel.getReviews(movieId, page)
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
fun ReviewList(reviews: List<ReviewsList>, paddingModifier: Modifier) {
    LazyColumn(
        paddingModifier
    ) {
        items(reviews.size) { i ->
            ReviewListItem(
                name = reviews[i].author,
                content = reviews[i].content
            )
        }
    }
}

@Composable
fun ReviewListItem(name: String, content: String) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Name : $name",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = "Review : $content",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}