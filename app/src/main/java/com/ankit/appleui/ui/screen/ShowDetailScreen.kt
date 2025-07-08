package com.ankit.appleui.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.ankit.appleui.ui.util.ImageResources

@Composable
fun ShowDetailScreen(
    showId: String,
    navController: NavController
) {
    val show = ImageResources.getShowItem(showId.toInt())

    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(show.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = show.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(0.5f)
            )
            Text(
                text = "Enjoy \"${show.title}\" only on Apple TV+.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}
