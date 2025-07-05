package com.ankit.appleui.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import androidx.compose.ui.tooling.preview.Preview
import com.ankit.appleui.ui.theme.AppleUITheme
import com.ankit.appleui.ui.util.ImageResources

data class ShowItem(
    val id: String,
    val title: String,
    val imageUrl: String? = null,
    val showTvBadge: Boolean = true
)

@Composable
fun ShowCard(
    show: ShowItem,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(170.dp, 250.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1C1C1E))
            .clickable(onClick = onClick)
    ) {
        var isLoading by remember { mutableStateOf(true) }
        
        // Show shimmer while loading
        if (isLoading) {
            ShimmerItem(
                modifier = Modifier.matchParentSize()
            )
        }
        
        // Show poster image
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(show.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "${show.title} poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
            onState = { state ->
                isLoading = state is AsyncImagePainter.State.Loading
            }
        )
        
        // TV+ badge (optional)
        if (show.showTvBadge) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .background(Color.White, RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = "tv+", 
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ShowCardPreview() {
    AppleUITheme {
        ShowCard(show = ImageResources.getShowItem(0))
    }
}
