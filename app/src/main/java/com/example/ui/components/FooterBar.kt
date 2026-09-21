package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun FooterBar() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        DeepCharcoal.copy(alpha = 0.92f),
                        ObsidianBlack
                    )
                )
            )
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Thin gold divider
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color.Transparent, GoldBright, GoldDark, Color.Transparent)
                    )
                )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "AIR TIME BLACK CAR & SUV SERVICE",
            fontFamily = FontFamily.Serif,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = GoldBright,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = "Luxury Travel. Professional Service. Every Journey.",
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = TextSilver,
            letterSpacing = 1.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Direct clickable contact badges in footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "+1 780 868 9010",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = GoldLight,
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+17808689010"))
                        context.startActivity(intent)
                    }
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )

            Text(text = "•", color = GoldDark, fontSize = 10.sp)

            Text(
                text = "info@airtimeblacklimo.com",
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = TextSilver,
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:info@airtimeblacklimo.com"))
                        context.startActivity(intent)
                    }
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )

            Text(text = "•", color = GoldDark, fontSize = 10.sp)

            Text(
                text = "www.airtimeblacklimo.com",
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = TextSilver,
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.airtimeblacklimo.com"))
                        context.startActivity(intent)
                    }
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Action Buttons: Call Now, Email, Website
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Call Now (Elevated Gold Button)
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+17808689010"))
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .weight(1.2f)
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldPrimary,
                    contentColor = ObsidianBlack
                ),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                    tint = ObsidianBlack,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "CALL NOW",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp,
                    color = ObsidianBlack
                )
            }

            // Email
            OutlinedButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:info@airtimeblacklimo.com"))
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = Brush.linearGradient(listOf(GoldDark, GoldBorder))
                ),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = GoldBright,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "EMAIL US",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldBright
                )
            }

            // Website
            OutlinedButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.airtimeblacklimo.com"))
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = Brush.linearGradient(listOf(GoldBorder, BorderCharcoal))
                ),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = null,
                    tint = TextSilver,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "WEBSITE",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSilver
                )
            }
        }
    }
}
