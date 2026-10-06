package com.webapp.thisweek.ui.srceen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.webapp.thisweek.R
import com.webapp.thisweek.ui.common.ProfileText

@Composable
fun ProfileScreen() {
    var isProfileBlured by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        // 프로필 사진
        Card {
            Box(
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(R.drawable.jena),
                    contentDescription = "프로필 이미지",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .blur(radius = (if (isProfileBlured) 16 else 0).dp),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .padding(top = 200.dp)
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.5f)
                                )
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        ProfileText("제나", MaterialTheme.typography.headlineLarge)
                        Spacer(Modifier.width(8.dp))
                        ProfileText("19")
                    }

                    ProfileText("직업 : 가수")
                    ProfileText("MBTI : ENFP")
                    Spacer(modifier = Modifier.height(8.dp))
                    ProfileText("까엉!")
                }
            }
        }

        Spacer(Modifier.weight(1.0f))

        // 만남 완료 버튼
        Button(
            onClick = {
                isProfileBlured = !isProfileBlured
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("만남 완료")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}