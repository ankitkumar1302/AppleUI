package com.ankit.appleui.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.view.WindowInsetsCompat
import com.ankit.appleui.ui.component.CtaSection
import com.ankit.appleui.ui.component.HeroCollage
import com.ankit.appleui.ui.component.SectionHeader
import com.ankit.appleui.ui.component.ShowCard
import com.ankit.appleui.ui.navigation.BottomNavBar
import com.ankit.appleui.ui.util.ImageResources
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val listState = rememberLazyListState()
    val pagerState = rememberPagerState { 5 }
    val coroutineScope = rememberCoroutineScope()
    
    // For collapsing toolbar behavior
    val topAppBarState = rememberTopAppBarState()
    
    // Show top app bar only when scrolled
    val showTopBar by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 150 }
    }
    
    // Variable to indicate if title/signin should be shown in hero or appbar
    val showInHero by remember {
        derivedStateOf { !showTopBar }
    }
    
    // Get status bar height
    val density = LocalDensity.current
    val statusBarHeightDp = with(density) { 
        WindowInsets.statusBars.getTop(density).toDp() 
    }
    
    // Animate the status bar background alpha
    val statusBarBackgroundAlpha by animateFloatAsState(
        targetValue = if (showTopBar) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "statusBarAlpha"
    )

    // Use Box as the root to control z-index for the status bar overlay
    Box(modifier = Modifier.fillMaxSize()) {
        // Status bar overlay that changes opacity when scrolling
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(statusBarHeightDp)
                .background(Color.Black.copy(alpha = statusBarBackgroundAlpha))
                .zIndex(10f)
        )
        
        // Main content scaffold
        Scaffold(
            containerColor = Color.Black,
            contentColor = Color.White,
            bottomBar = {
                // Custom bottom navigation bar
                BottomNavBar()
            },
            topBar = {
                // Only show the top app bar when scrolled down
                AnimatedVisibility(
                    visible = showTopBar,
                    enter = fadeIn(animationSpec = tween(300)) + 
                            slideInVertically(
                                animationSpec = tween(300),
                                initialOffsetY = { -it }
                            ),
                    exit = fadeOut(animationSpec = tween(300)) + 
                        slideOutVertically(
                                animationSpec = tween(300),
                                targetOffsetY = { -it }
                        )
                ) {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Black,
                            titleContentColor = Color.White
                        ),
                        title = {
                            Text(
                                text = "Apple TV+",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        actions = {
                            // Sign In Button
                            IconButton(onClick = { /* Sign in action */ }) {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "Sign In",
                                    tint = Color.White
                                )
                            }
                        },
                        modifier = Modifier.statusBarsPadding()
                    )
                }
            }
        ) { innerPadding ->
            // Main scrollable content
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(
                    top = 0.dp, // No top padding as hero covers status bar
                    bottom = innerPadding.calculateBottomPadding()
                ),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Combined Hero + CTA Section
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        // Hero Collage
                        HeroCollage(
                            pagerState = pagerState,
                            showControls = showInHero, // Only show controls in hero when not in app bar
                            modifier = Modifier
                        )
                        
                        // CTA Section seamlessly overlaid on hero with a gradient transition
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .offset(y = (-10).dp) // Moved down by changing from -30dp to -10dp
                                .zIndex(3f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(30.dp)
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.Black
                                            )
                                        )
                                    )
                            )
                            
                            // Pass pagerState to CTA section for the indicators
                            CtaSection(
                                pagerState = pagerState,
                                pageCount = 5
                            )
                        }
                    }
                }
                
                // Add some spacing after combined Hero+CTA section
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                
                // Must-See Hits Section
                item {
                    SectionHeader(title = "Must-See Hits")
                    
                    // Generate show items using our utility class
                    val shows = List(10) { index ->
                        ImageResources.getShowItem(index)
                    }
                    
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(shows) { show ->
                            ShowCard(
                                show = show
                            )
                        }
                    }
                }
                
                // Top 10 TV Shows Section
                item {
                    SectionHeader(title = "Top 10 TV Shows")
                    
                    // Generate another set of shows
                    val topShows = List(10) { index ->
                        ImageResources.getShowItem(index + 5) // offset to get different images
                    }
                    
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(topShows) { show ->
                            ShowCard(
                                show = show
                            )
                        }
                    }
                }
                
                // Coming Soon Section  
                item {
                    SectionHeader(title = "Coming Soon")
                    
                    // Generate another set of shows
                    val comingSoonShows = List(10) { index ->
                        ImageResources.getShowItem(index + 2) // different offset
                    }
                    
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(comingSoonShows) { show ->
                            ShowCard(
                                show = show
                            )
                        }
                    }
                    
                    // Add extra space at the bottom to ensure content isn't cut off by navigation
                    Spacer(modifier = Modifier.height(64.dp))
                }
            }
        }
    }
} 