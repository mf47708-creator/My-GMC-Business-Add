package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.model.SlideInfo
import com.example.ui.theme.*

@Composable
fun SlideContent(
    slide: SlideInfo,
    modifier: Modifier = Modifier
) {
    var showOverlayInfo by remember { mutableStateOf(true) }
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                showOverlayInfo = !showOverlayInfo
            }
    ) {
        // Fullscreen Cinematic Vehicle & Chauffeur Photograph
        Image(
            painter = painterResource(id = slide.imageRes),
            contentDescription = "${slide.title} - ${slide.category}",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Luxury Vignette Overlay (Leaves the center vivid while ensuring legibility top & bottom)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ObsidianBlack.copy(alpha = 0.85f),
                            ObsidianBlack.copy(alpha = 0.20f),
                            Color.Transparent,
                            ObsidianBlack.copy(alpha = 0.45f),
                            ObsidianBlack.copy(alpha = 0.92f),
                            ObsidianBlack.copy(alpha = 0.98f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        // Floating Caption & Story Card (Anchored right above the bottom pagination and footer)
        AnimatedVisibility(
            visible = showOverlayInfo,
            enter = fadeIn() + slideInVertically { it / 2 },
            exit = fadeOut() + slideOutVertically { it / 2 },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .padding(bottom = 175.dp) // Generous clearance above the bottom pagination and footer bar
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 520.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GlassSurface),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            GoldDark.copy(alpha = 0.6f),
                            GoldBright.copy(alpha = 0.9f),
                            GoldDark.copy(alpha = 0.6f)
                        )
                    ),
                    width = 1.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Category & Slide Number Tag
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = slide.slideNumber,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            color = GoldBright
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .size(4.dp)
                                .clip(CircleShape)
                                .background(GoldPrimary)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = slide.category.uppercase(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldLight,
                            letterSpacing = 2.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Title
                    Text(
                        text = slide.title,
                        fontFamily = FontFamily.Serif,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldBright,
                        textAlign = TextAlign.Center,
                        letterSpacing = 1.sp
                    )

                    // Subtitle (if available)
                    if (!slide.subtitle.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = slide.subtitle,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextWhite,
                            textAlign = TextAlign.Center,
                            letterSpacing = 1.5.sp
                        )
                    }

                    // Description
                    if (!slide.description.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = slide.description,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.sp,
                            color = TextSilver,
                            textAlign = TextAlign.Center,
                            lineHeight = 17.sp,
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    }

                    // Key Highlights Chips
                    if (slide.highlights.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            slide.highlights.forEach { highlight ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(DeepCharcoal.copy(alpha = 0.8f))
                                        .border(0.6.dp, GoldBorder, RoundedCornerShape(12.dp))
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = highlight,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = GoldLight,
                                        letterSpacing = 0.5.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Tap for fullscreen hint (Subtle pill centered at the bottom of the card)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 152.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(ObsidianBlack.copy(alpha = 0.6f))
                .border(0.5.dp, BorderCharcoal, RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp, vertical = 3.dp)
        ) {
            Text(
                text = if (showOverlayInfo) "Tap photo to expand view" else "Tap to show details",
                fontSize = 9.sp,
                fontWeight = FontWeight.Normal,
                color = TextMuted,
                letterSpacing = 0.5.sp
            )
        }
    }
}
