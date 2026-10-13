package com.webapp.thisweek.ui.page

import android.R.attr.text
import android.R.id.message
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.webapp.thisweek.data.model.User
import com.webapp.thisweek.ui.common.PhonePreview
import com.webapp.thisweek.ui.common.PrimaryButton
import com.webapp.thisweek.viewmodel.ChatViewModel
import com.webapp.thisweek.viewmodel.MatchViewModel
import com.webapp.thisweek.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomPage(
    userViewModel: UserViewModel = viewModel(),
    matchViewModel: MatchViewModel = viewModel(),
    chatViewModel: ChatViewModel = viewModel()
) {
    val context = LocalContext.current

    var input by remember { mutableStateOf("") }

    val currentUserId = userViewModel.currentUserId

    // 내 신청서 가져오기 (매치 아이디 가져오기 위해)
    val myApp = currentUserId?.let {
        matchViewModel.findAppByUserId(it)
    }
    val matchId = myApp?.matchId ?: ""

    // 상대 신청서 가져오기
    val partnerApp = myApp?.let {
        matchViewModel.findPartnerApp(myApp)
    }
    // 상대 유저 id 가져오기
    val partnerId = partnerApp?.userId ?: ""
    val partner = userViewModel.getUserById(partnerId) ?: User()

    // 리액트 useEffect에서 배웠던 의존성 배열과 클린업 함수와 완전히 동일
    DisposableEffect(matchId) {
        if (matchId.isNotEmpty()) {
            chatViewModel.startListening(matchId)
        }

        onDispose {
            chatViewModel.stopListening()
        }
    }

    val listState = rememberLazyListState()
    LaunchedEffect(chatViewModel.messageList.size) {
        if (chatViewModel.messageList.isNotEmpty()) {
            listState.animateScrollToItem(chatViewModel.messageList.size - 1)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = partner.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            placeholder = rememberVectorPainter(Icons.Default.Person),
                            error = rememberVectorPainter(Icons.Default.Person),
                            fallback = rememberVectorPainter(Icons.Default.Person),
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(partner.nick)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .imePadding() // 키보드 높이만큼 아래 여백 추가
                .fillMaxSize()
        ) {
            // 메세지 스크롤 영역
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1.0f)
            ) {
                items(chatViewModel.messageList) { message ->
                    val isMine: Boolean = message.senderId == currentUserId

                    MessageBubble(
                        text = message.text,
                        isMine = isMine
                    )
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
                    value = input,
                    onValueChange = { input = it }
                )
                Spacer(Modifier.width(8.dp))
                PrimaryButton("전송") {
                    if (currentUserId == null || myApp == null) return@PrimaryButton

                    chatViewModel.sendMessage(
                        matchId = myApp.matchId,
                        senderId = currentUserId,
                        text = input
                    ) {
                        input = ""
                    }
                }

            }
            /*PrimaryButton("테스트") {
                chatViewModel.delay()

                Toast.makeText(context, "adsfasdf", Toast.LENGTH_SHORT).show()

                val start = System.currentTimeMillis()

                while (System.currentTimeMillis() - start < 5000) {
                    // 메인 스레드를 계속 점유
                }
            }*/
        }
    }
}

@PhonePreview
@Composable
fun ChatRoomPagePreview() {
    ChatRoomPage()
}

@Composable
fun MessageBubble(text: String, isMine: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalArrangement = if (isMine) Arrangement.End else Arrangement.Start
    ) {
        Text(
            text = text,
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
    MessageBubble(
        text = "가짜 채팅입니다.",
        isMine = true
    )
}













