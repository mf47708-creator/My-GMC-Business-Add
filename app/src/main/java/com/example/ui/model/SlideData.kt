package com.example.ui.model

import androidx.annotation.DrawableRes

data class SlideInfo(
    val id: Int,
    val slideNumber: String,
    val category: String,
    val title: String,
    val subtitle: String? = null,
    val description: String? = null,
    val highlights: List<String> = emptyList(),
    @DrawableRes val imageRes: Int
)

