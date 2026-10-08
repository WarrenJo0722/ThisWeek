package com.webapp.thisweek.ui.srceen.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun WaitingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = """
            아직 매칭전문가 민지가
            꿈에 그리던 이상형을
            선별하고 있습니다.
            잠시만 기다려주세요.
        """.trimIndent(),
            textAlign = TextAlign.Center
        )
    }
}









