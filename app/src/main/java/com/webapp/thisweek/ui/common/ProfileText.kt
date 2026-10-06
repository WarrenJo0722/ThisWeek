package com.webapp.thisweek.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileText(
    text: String,
    style: TextStyle = MaterialTheme.typography.headlineSmall
) {
    Text(
        text,
        color = Color.White,
        style = style.copy(
            shadow = Shadow(
                color = Color.Black.copy(alpha = 0.3f),
                offset = Offset(x = 0f, y = 4f),
                blurRadius = 8f
            )
        ),
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
fun ProfileTextPreview() {
    ProfileText("텍스트", MaterialTheme.typography.headlineSmall)
}