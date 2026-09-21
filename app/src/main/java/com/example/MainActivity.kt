package com.example

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.FooterBar
import com.example.ui.components.HeaderBar
import com.example.ui.components.SlideContent
import com.example.ui.model.SlideInfo
import com.example.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AirTimeApp()
            }
        }
    }
}

@Composable
fun AirTimeApp() {
    val slides = remember {
        listOf(
            SlideInfo(
                id = 1,
                slideNumber = "01",
                category = "Fleet & Chauffeur",
                title = "AIR TIME",
                subtitle = "BLACK CAR & SUV SERVICE",
                description = "Edmonton's premier upscale transportation. Professional, comfortable and reliable black car & SUV service for every occasion.",
                highlights = listOf("Luxury SUVs", "Professional Chauffeurs", "Edmonton & Area"),
                imageRes = R.drawable.img_slide_welcome
            ),
            SlideInfo(
                id = 2,
                slideNumber = "02",
                category = "Airport Service",
                title = "Airport Transfers",
                subtitle = "SEAMLESS PICK-UP & DROP-OFF",
                description = "We take pride in providing luxurious airport transfers, going above and beyond with flight monitoring and seamless terminal pick-up and drop-off.",
                highlights = listOf("Flight Tracking", "24/7 Available", "Terminal Meet & Greet"),
                imageRes = R.drawable.img_slide_airport
            ),
            SlideInfo(
                id = 3,
                slideNumber = "03",
                category = "Business & VIP",
                title = "Executive Travel",
                subtitle = "FIRST-CLASS EXECUTIVE TRANSPORTATION",
                description = "Discreet, confidential and punctual executive travel. Focus on what matters while our professional chauffeurs handle your journey with precision and care.",
                highlights = listOf("Confidential & Discreet", "Punctual", "Executive Comfort"),
                imageRes = R.drawable.img_slide_business
            ),
            SlideInfo(
                id = 4,
                slideNumber = "04",
                category = "Weddings & Productions",
                title = "Weddings & Special Occasions",
                subtitle = "UNFORGETTABLE LUXURY JOURNEYS",
                description = "Make your special day extraordinary with immaculate luxury SUVs for the bride, groom, and wedding party, plus camera-ready vehicles for film shoots.",
                highlights = listOf("Immaculate Fleet", "Camera Ready", "Dedicated Day"),
                imageRes = R.drawable.img_slide_wedding
            ),
            SlideInfo(
                id = 5,
                slideNumber = "05",
                category = "Out of Town & Hourly",
                title = "Alberta Highway & Rockies",
                subtitle = "STANDBY CHAUFFEUR & REGIONAL TRAVEL",
                description = "Travel beyond Edmonton in total comfort. Available for journeys to Jasper, Banff, Lake Louise, and Calgary, or reserve a chauffeur by the hour.",
                highlights = listOf("Jasper & Banff", "Hourly Standby", "Alberta-wide"),
                imageRes = R.drawable.img_slide_rockies
            ),
            SlideInfo(
                id = 6,
                slideNumber = "06",
                category = "Edmonton Events",
                title = "Rogers Place & Events",
                subtitle = "OILERS GAMES & MAJOR ARENA CONCERTS",
                description = "Arrive in style for Edmonton Oilers games, concerts, and major sporting events at Rogers Place and Commonwealth Stadium without traffic stress.",
                highlights = listOf("VIP Arena Drop-off", "Oilers Games", "Spacious Group SUVs"),
                imageRes = R.drawable.img_slide_events
            )
        )
    }

    val pagerState = rememberPagerState(initialPage = 0) { slides.size }
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    var isAutoPlayEnabled by remember { mutableStateOf(false) }

    // Auto-advance slideshow timer when enabled
    LaunchedEffect(isAutoPlayEnabled, pagerState.currentPage) {
        if (isAutoPlayEnabled) {
            delay(5000)
            val nextPage = (pagerState.currentPage + 1) % slides.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ObsidianBlack)
            .focusRequester(focusRequester)
            .focusable()
            .onKeyEvent { keyEvent ->
                if (keyEvent.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
                    when (keyEvent.nativeKeyEvent.keyCode) {
                        KeyEvent.KEYCODE_DPAD_LEFT -> {
                            if (pagerState.currentPage > 0) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                                true
                            } else false
                        }
                        KeyEvent.KEYCODE_DPAD_RIGHT -> {
                            if (pagerState.currentPage < slides.size - 1) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                                true
                            } else false
                        }
                        else -> false
                    }
                } else false
            }
    ) {
        // Horizontal Slideshow Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->
            val currentSlide = slides[pageIndex]
            SlideContent(slide = currentSlide)
        }

        // Top Slim Luxury Header
        HeaderBar(
            currentPage = pagerState.currentPage,
            onNavigateToPage = { targetPage ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(targetPage)
                }
            }
        )

        // Top Controls: Slideshow Counter & AutoPlay Toggle
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
                .padding(top = 62.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // AutoPlay Play/Pause Button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(GlassSurface)
                    .border(0.8.dp, GoldBorder, RoundedCornerShape(16.dp))
                    .clickable { isAutoPlayEnabled = !isAutoPlayEnabled }
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isAutoPlayEnabled) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isAutoPlayEnabled) "Pause Slideshow" else "Auto-play Slideshow",
                        tint = if (isAutoPlayEnabled) GoldBright else TextSilver,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = if (isAutoPlayEnabled) "AUTO" else "PLAY",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isAutoPlayEnabled) GoldBright else TextSilver
                    )
                }
            }

            // Slide Counter (01 / 06)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(GlassSurface)
                    .border(0.8.dp, GoldBorder, RoundedCornerShape(16.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = slides[pagerState.currentPage].slideNumber,
                        fontFamily = FontFamily.Serif,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldBright
                    )
                    Text(
                        text = " / ",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = GoldDark
                    )
                    Text(
                        text = "06",
                        fontFamily = FontFamily.Serif,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextSilver
                    )
                }
            }
        }

        // Left Navigation Arrow Button
        AnimatedVisibility(
            visible = pagerState.currentPage > 0,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 8.dp)
        ) {
            IconButton(
                onClick = {
                    if (pagerState.currentPage > 0) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(GlassSurface)
                    .border(1.dp, GoldBorder, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "Previous Photo",
                    tint = GoldBright,
                    modifier = Modifier
                        .size(16.dp)
                        .offset(x = 2.dp)
                )
            }
        }

        // Right Navigation Arrow Button
        AnimatedVisibility(
            visible = pagerState.currentPage < slides.size - 1,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
        ) {
            IconButton(
                onClick = {
                    if (pagerState.currentPage < slides.size - 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(GlassSurface)
                    .border(1.dp, GoldBorder, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "Next Photo",
                    tint = GoldBright,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // Horizontal Pagination Dots (positioned above footer)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 110.dp) // Sits neatly above the footer bar
                .clip(RoundedCornerShape(20.dp))
                .background(GlassSurface)
                .border(0.8.dp, BorderCharcoal, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            slides.indices.forEach { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .height(6.dp)
                        .width(if (isSelected) 24.dp else 6.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) GoldBright else BorderCharcoal)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                        .animateContentSize()
                )
            }
        }

        // Bottom Luxury Black Footer with Contact Direct Actions
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            FooterBar()
        }
    }
}
