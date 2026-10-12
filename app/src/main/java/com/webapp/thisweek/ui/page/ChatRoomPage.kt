package com.webapp.thisweek.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.webapp.thisweek.ui.common.PhonePreview
import com.webapp.thisweek.ui.common.PrimaryButton

@Composable
fun ChatRoomPage() {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // 메세지 스크롤 영역
            LazyColumn(
                modifier = Modifier
                    .weight(1.0f)
            ) {
                item {
                    MessageBubble(true)
                    MessageBubble(false)
                }

                items(10) {

                }
            }

            // 인풋 영역
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { }

                )
                Spacer(Modifier.width(8.dp))
                PrimaryButton("전송")
            }
        }
    }
}

@PhonePreview
@Composable
fun ChatRoomPagePreview() {
    ChatRoomPage()
}

@Composable
fun MessageBubble(isMine: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalArrangement = if (isMine) Arrangement.End else Arrangement.Start
    ) {
        Text(
            text = "반갑습니다.",
            color = Color.White,
            modifier = Modifier
                .widthIn(max = 260.dp)
                .background(
                    color = if (isMine) MaterialTheme.colorScheme.primary else Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Preview
@Composable
fun MessageBubblePreview() {
    MessageBubble(isMine = true)
}













