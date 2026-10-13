package com.webapp.thisweek.ui.srceen.match

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.webapp.thisweek.R
import com.webapp.thisweek.data.model.Gender
import com.webapp.thisweek.data.model.User
import com.webapp.thisweek.ui.common.PrimaryButton
import com.webapp.thisweek.ui.common.ProfileText
import com.webapp.thisweek.ui.common.VerticalSpacer

@Composable
fun ProfileScreen(
    navController: NavController = rememberNavController(),
    user: User
) {
    var isProfileBlured by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        // 프로필 사진
        Card {
            Box(
                contentAlignment = Alignment.BottomStart
            ) {
                AsyncImage(
                    model = user.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,

                    // 불러오는 중에
                    placeholder = rememberVectorPainter(Icons.Default.Person),

                    // 실패했을 때
                    error = rememberVectorPainter(Icons.Default.Person),

                    // 이미지 uri가 널일때
                    fallback = rememberVectorPainter(Icons.Default.Person),

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .blur(radius = (if (isProfileBlured) 16 else 0).dp),
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
                        ProfileText(user.nick, MaterialTheme.typography.headlineLarge)
                        Spacer(Modifier.width(8.dp))
                        ProfileText(user.age.toString())
                    }

                    ProfileText("직업 : ${user.job}")
                    ProfileText("MBTI : ${user.mbti}")
                    Spacer(modifier = Modifier.height(8.dp))
                    ProfileText(user.greeting)
                }
            }
        }

        Spacer(Modifier.weight(1.0f))

        VerticalSpacer()

        PrimaryButton("대화 하기") {
            navController.navigate("chatRoom")
        }

        VerticalSpacer()

        // 만남 완료 버튼
        PrimaryButton("만남 완료") {
            isProfileBlured = !isProfileBlured
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(user = User(
        nick = "제나",
        email = "",
        password = "",
        age = 19,
        gender = Gender.FEMALE,
        job = "가수",
        mbti = "ENFP",
        greeting = "까엉!!"
    ))
}