package com.ankit.appleui.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.pager.rememberPagerState
import com.ankit.appleui.ui.theme.AppleUITheme
import kotlinx.coroutines.launch

@Composable
fun CtaSection(
    pagerState: PagerState? = null,
    pageCount: Int = 5
) {
    val coroutineScope = rememberCoroutineScope()
    val haptic = LocalHapticFeedback.current
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Headline - Watch on Apple TV app
        Text(
            text = "Watch on the Apple TV app",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        
        // Subhead - Enjoy Apple Originals
        Text(
            text = "Enjoy all Apple Originals — only on Apple TV+",
            fontSize = 15.sp,
            color = Color.White.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        
        // Button - Start Free Trial
        Button(
            onClick = { /* Start free trial action */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp) // No elevation (shadowless)
        ) {
            Text(
                text = "Start Free Trial",
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium
            )
        }
        
        // Price hint
        Text(
            text = "3 months free, then $9.99/month",
            fontSize = 13.sp,
            color = Color.White.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        // Apple TV+ style page indicators below all content
        if (pagerState != null) {
            Row(
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                AppleTVStyleIndicator(
                    pageCount = pageCount,
                    currentPage = pagerState.currentPage,
                    onPageSelected = { page ->
                        if (page != pagerState.currentPage) {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page)
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AppleTVStyleIndicator(
    pageCount: Int,
    currentPage: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
    ) {
        for (i in 0 until pageCount) {
            val isSelected = i == currentPage
            
            Box(
                modifier = Modifier
                    .size(if (isSelected) 7.dp else 7.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color.White else Color.White.copy(alpha = 0.2f)
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // No ripple effect
                    ) {
                        onPageSelected(i)
                    }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun CtaSectionPreview() {
    AppleUITheme {
        val pagerState = rememberPagerState { 5 }
        CtaSection(pagerState = pagerState)
    }
}
