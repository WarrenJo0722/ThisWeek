package com.webapp.thisweek.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun IntroPage(navController: NavController = rememberNavController()) {
    Scaffold() { innerPadding ->
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
            Button(onClick = {
                navController.navigate("main")
            }) {
                Text("소개팅 신청하러 가기")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IntroPagePreview() {
    IntroPage()
}