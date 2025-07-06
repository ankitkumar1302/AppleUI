package com.ankit.appleui.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current
    
    Row(
        modifier = modifier
            .width(160.dp)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 0 until pageCount) {
            val isSelected = i == currentPage
            
            // Animation values
            val width by animateFloatAsState(
                targetValue = if (isSelected) 24f else 8f,
                animationSpec = tween(durationMillis = 300, easing = LinearEasing),
                label = "indicator_width"
            )
            
            val alpha by animateFloatAsState(
                targetValue = if (isSelected) 1f else 0.5f,
                animationSpec = tween(durationMillis = 300),
                label = "indicator_alpha"
            )
            
            val color by animateColorAsState(
                targetValue = if (isSelected) 
                    Color.White 
                else 
                    Color(0xFF666666),
                animationSpec = tween(durationMillis = 300),
                label = "indicator_color"
            )
            
            // Pill-shaped indicator that stretches when selected
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
                    .size(32.dp) // Larger clickable area
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                if (i != currentPage) {
                                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    onPageSelected(i)
                                }
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                // The custom pill-shaped indicator
                Canvas(modifier = Modifier.size(24.dp)) {
                    // Draw an elongated pill for the selected page or a small circle for unselected
                    drawRoundRect(
                        color = color,
                        size = Size(width, 8f),
                        cornerRadius = CornerRadius(4f, 4f),
                        topLeft = Offset((size.width - width) / 2, (size.height - 8f) / 2),
                        alpha = alpha
                    )
                }
            }
        }
    }
} 