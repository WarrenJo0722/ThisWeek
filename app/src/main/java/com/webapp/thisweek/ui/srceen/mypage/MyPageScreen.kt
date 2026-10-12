package com.webapp.thisweek.ui.srceen.mypage

import android.R.attr.contentDescription
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.webapp.thisweek.ui.common.PrimaryButton
import com.webapp.thisweek.viewmodel.UserViewModel

@Composable
fun MyPageScreen(
    userViewModel: UserViewModel = viewModel(),
    onLogout: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = userViewModel.currentUser?.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,

            // 불러오는 중에
            placeholder = rememberVectorPainter(Icons.Default.Person),

            // 실패했을 때
            error = rememberVectorPainter(Icons.Default.Person),

            // 이미지 uri가 널일때
            fallback = rememberVectorPainter(Icons.Default.Person),

            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Text(
            text = "${userViewModel.currentUser?.nick ?: ""} 님",
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.weight(1.0f))

        PrimaryButton("로그아웃") {
            userViewModel.logout()
            onLogout()
        }
    }
}









