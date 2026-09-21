package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.*

@Composable
fun HeaderBar(
    currentPage: Int,
    onNavigateToPage: (Int) -> Unit
) {
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        ObsidianBlack.copy(alpha = 0.95f),
                        DeepCharcoal.copy(alpha = 0.85f),
                        Color.Transparent
                    )
                )
            )
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Brand Emblem & Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onNavigateToPage(0) }
                        .padding(vertical = 4.dp)
                ) {
                    // Stylized luxury Logo emblem
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .border(1.dp, GoldPrimary, CircleShape)
                            .background(DeepCharcoal),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_airtime_logo),
                            contentDescription = "Air Time Logo",
                            modifier = Modifier.size(38.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = "AIR TIME",
                            fontFamily = FontFamily.Serif,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldBright,
                            letterSpacing = 2.sp
                        )
                        Text(
                            text = "BLACK CAR & SUV SERVICE",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextSilver,
                            letterSpacing = 1.2.sp
                        )
                    }
                }

                // Desktop / Tablet Navigation Links & Direct Action
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Call Button Pill
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(GlassSurfaceLight)
                            .border(0.8.dp, GoldBorder, RoundedCornerShape(20.dp))
                            .clickable {
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+17808689010"))
                                context.startActivity(intent)
                            }
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Call",
                            tint = GoldBright,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "+1 780 868 9010",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = GoldLight
                        )
                    }

                    // Menu / Navigation toggle
                    IconButton(
                        onClick = { showMenu = !showMenu },
                        modifier = Modifier
                            .size(36.dp)
                            .background(CardCharcoal, CircleShape)
                            .border(1.dp, if (showMenu) GoldPrimary else BorderCharcoal, CircleShape)
                    ) {
                        Icon(
                            imageVector = if (showMenu) Icons.Default.Close else Icons.Default.Menu,
                            contentDescription = "Navigation Menu",
                            tint = GoldPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Expandable Navigation & Quick Contact Drawer
            AnimatedVisibility(
                visible = showMenu,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = GlassSurface),
                    border = CardDefaults.outlinedCardBorder().copy(
                        brush = Brush.horizontalGradient(listOf(GoldDark, GoldBright, GoldDark))
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Section Navigation
                        Text(
                            text = "NAVIGATION",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldMuted,
                            letterSpacing = 1.5.sp
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            NavChip(label = "Home", isSelected = currentPage == 0) {
                                onNavigateToPage(0)
                                showMenu = false
                            }
                            NavChip(label = "Airport", isSelected = currentPage == 1) {
                                onNavigateToPage(1)
                                showMenu = false
                            }
                            NavChip(label = "Business", isSelected = currentPage == 2) {
                                onNavigateToPage(2)
                                showMenu = false
                            }
                            NavChip(label = "Weddings", isSelected = currentPage == 3) {
                                onNavigateToPage(3)
                                showMenu = false
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            NavChip(label = "Out of Town & Hourly", isSelected = currentPage == 4) {
                                onNavigateToPage(4)
                                showMenu = false
                            }
                            NavChip(label = "Edmonton Events", isSelected = currentPage == 5) {
                                onNavigateToPage(5)
                                showMenu = false
                            }
                        }

                        Divider(color = BorderCharcoal, thickness = 0.8.dp)

                        // Contact Details Row
                        Text(
                            text = "DIRECT CONCIERGE CONTACT",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldMuted,
                            letterSpacing = 1.5.sp
                        )

                        ContactRow(
                            icon = Icons.Default.Phone,
                            title = "Phone",
                            value = "+1 780 868 9010",
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+17808689010"))
                                context.startActivity(intent)
                            }
                        )

                        ContactRow(
                            icon = Icons.Default.Email,
                            title = "Email",
                            value = "info@airtimeblacklimo.com",
                            onClick = {
                                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:info@airtimeblacklimo.com"))
                                context.startActivity(intent)
                            }
                        )

                        ContactRow(
                            icon = Icons.Default.Language,
                            title = "Website",
                            value = "www.airtimeblacklimo.com",
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.airtimeblacklimo.com"))
                                context.startActivity(intent)
                            }
                        )

                        Button(
                            onClick = {
                                showMenu = false
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+17808689010"))
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = ObsidianBlack),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                tint = ObsidianBlack,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "CALL CONCIERGE: +1 780 868 9010",
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.2.sp,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NavChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(if (isSelected) GoldPrimary else CardCharcoal)
            .border(1.dp, if (isSelected) GoldBright else BorderCharcoal, RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 7.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) ObsidianBlack else TextWhite
        )
    }
}

@Composable
private fun ContactRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = GoldPrimary,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$title: ",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextMuted
        )
        Text(
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = TextWhite
        )
    }
}
