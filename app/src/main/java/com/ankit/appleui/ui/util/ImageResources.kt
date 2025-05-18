package com.ankit.appleui.ui.util

import com.ankit.appleui.ui.component.PosterItem
import com.ankit.appleui.ui.component.ShowItem

/**
 * Provides URLs for high-quality sample images from Unsplash
 */
object ImageResources {
    // Hero images for large banners (16:9 cinematic images)
    val heroImages = listOf(
        "https://images.unsplash.com/photo-1626814026160-2237a95fc5a0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1320&h=495",
        "https://images.unsplash.com/photo-1585951237313-1979e4df7385?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1320&h=495",
        "https://images.unsplash.com/photo-1536440136628-849c177e76a1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1320&h=495",
        "https://images.unsplash.com/photo-1618172193622-ae2d025f4032?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1320&h=495",
        "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1320&h=495"
    )

    // Show card images for content carousels (2:3 poster ratio images)
    val showImages = listOf(
        "https://images.unsplash.com/photo-1594909122845-11baa439b7bf?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&h=750", // Sci-fi scene
        "https://images.unsplash.com/photo-1543536448-1e76fc2795bf?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&h=750", // Cinema
        "https://images.unsplash.com/photo-1626563120636-c6abd33d23ea?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&h=750", // TV remote
        "https://images.unsplash.com/photo-1579762593175-20226054cad0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&h=750", // Popcorn
        "https://images.unsplash.com/photo-1586899028174-e7098604235b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&h=750", // Woman watching
        "https://images.unsplash.com/photo-1611162618071-b39a2ec055fb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&h=750", // Streaming
        "https://images.unsplash.com/photo-1585951237318-9ea5e175b891?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&h=750", // TV
        "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&h=750", // Film
        "https://images.unsplash.com/photo-1598899134739-24c46f58b8c0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&h=750", // Movie
        "https://images.unsplash.com/photo-1478720568477-152d9b164e26?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=500&h=750"  // Sunset
    )

    // Show title map (updated to match the content theme of the images)
    val showTitles = mapOf(
        0 to "Future World",
        1 to "The Director",
        2 to "Remote Control",
        3 to "Movie Night",
        4 to "The Viewer",
        5 to "Streaming Wars",
        6 to "Screen Time",
        7 to "Film Noir",
        8 to "The Premiere",
        9 to "Sunset Boulevard"
    )

    // Function to generate a show item with real data
    fun getShowItem(index: Int): ShowItem {
        val adjustedIndex = index % showImages.size
        return ShowItem(
            id = index.toString(),
            title = showTitles[adjustedIndex] ?: "Show $index",
            imageUrl = showImages[adjustedIndex],
            showTvBadge = true
        )
    }

    // Function to generate a poster item for hero carousel
    fun getPosterItem(index: Int): PosterItem {
        val adjustedIndex = index % heroImages.size
        return PosterItem(
            id = index.toString(),
            imageUrl = heroImages[adjustedIndex],
            title = showTitles[adjustedIndex] ?: "Show $index"
        )
    }
} 