package com.webapp.thisweek.ui.page

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webapp.thisweek.ui.common.PrimaryButton
import com.webapp.thisweek.viewmodel.UserViewModel

@Composable
fun IntroPage(
    navController: NavController = rememberNavController(),
    userViewModel: UserViewModel = viewModel()
) {
    val context = LocalContext.current
    var count by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = Color.White,
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = """
                 <안내>
                 저희 이번주 소개팅은
                 소개팅 문화의 발전을 위해
                 일주일에 한 번 소개팅 상대를 추천합니다.
                 소개팅을 신청해서 매칭이 되면,
                 꼭 만남이 성사 되어야 하며,
                 이를 어길시 더 이상 서비스를
                 사용할 수 없습니다.
             """.trimIndent(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(16.dp))
                PrimaryButton("소개팅 하러 가기") {
                    if (userViewModel.currentUserId == null) {
                        // 세션 없을 때 -> 로그인 화면으로
                        navController.navigate("login") {
                            popUpTo("intro") { inclusive = true}
                        }
                    } else {
                        // 세션 있을 때 -> 메인 화면으로
                        navController.navigate("main") {
                            popUpTo("intro") { inclusive = true}
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        count++
                        if (count == 1) {
                            navController.navigate("admin")
                        }
                    }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IntroPagePreview() {
    IntroPage()
}