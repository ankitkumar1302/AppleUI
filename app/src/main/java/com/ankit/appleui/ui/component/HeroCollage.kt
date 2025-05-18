package com.ankit.appleui.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.ankit.appleui.ui.util.ImageResources

// Sample data for hero posters
data class PosterItem(val id: String, val imageUrl: String, val title: String)

// Generate hero posters from our utility class
val heroPosters = List(5) { index ->
    ImageResources.getPosterItem(index)
}

@Composable
fun HeroCollage(
    pagerState: PagerState,
    showControls: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(550.dp) // Further increased height for a more immersive experience
            .graphicsLayer { alpha = 0.99f } // Alternative to CompositingStrategy
    ) {
        // Hero pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f) // Set a base z-index for the hero image
        ) { page ->
            val poster = heroPosters[page]
            
            Box(modifier = Modifier.fillMaxSize()) {
                var isLoading by remember { mutableStateOf(true) }
                
                // Show shimmer while loading
                if (isLoading) {
                    ShimmerItem(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(poster.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "${poster.title} poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    onState = { state ->
                        isLoading = state is AsyncImagePainter.State.Loading
                    }
                )
            }
        }

        // Top gradient scrim for status bar legibility
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.8f), // More opaque for better status bar visibility
                            Color.Transparent
                        ),
                        startY = 0f,
                        endY = 120f
                    )
                )
                .zIndex(2f) // Above the hero image
        )

        // Apple TV+ title at TOP left (shows or hides based on scroll position)
        AnimatedVisibility(
            visible = showControls,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300)),
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(start = 32.dp, top = 16.dp)
                .zIndex(5f) // Higher z-index for controls
        ) {
            Text(
                text = "Apple TV+",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp,
                color = Color.White
            )
        }

        // Sign In button at the top right (always visible)
        AnimatedVisibility(
            visible = showControls,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300)),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
                .zIndex(5f) // Higher z-index for controls
        ) {
            TextButton(
                onClick = { /* Sign in action */ },
                modifier = Modifier.padding(end = 16.dp, top = 8.dp)
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Sign In",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
                Text(
                    text = "Sign In",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }

        // Bottom gradient scrim for text legibility
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(300.dp) // Increased gradient height for better text legibility
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.9f)
                        ),
                        startY = 0f,
                        endY = 300f
                    )
                )
                .zIndex(2f) // Above the hero image but below indicators
        )
    }
} 