package com.ankit.appleui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.ankit.appleui.ui.screen.HomeScreen
import com.ankit.appleui.ui.theme.AppleUITheme

class MainActivity : ComponentActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Make sure we're drawing edge to edge - essential for proper insets handling
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        // Set status bar and navigation bar colors
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.BLACK
        
        // Configure insets controller to handle system bar appearance
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        
        // Set status bar and navigation bar appearance (light or dark)
        controller.isAppearanceLightStatusBars = false
        controller.isAppearanceLightNavigationBars = false
        
        // Handle cutouts for devices with notches
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = 
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        
        // Handle older Android versions specially to ensure proper layout
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            )
        }
        
        setContent {
            AppleUITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
    
    // Ensure proper cleanup when the app is minimized or closed
    override fun onDestroy() {
        super.onDestroy()
        window.decorView.rootView?.clearFocus()
    }
}