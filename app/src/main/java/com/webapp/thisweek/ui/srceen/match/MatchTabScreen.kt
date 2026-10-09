package com.webapp.thisweek.ui.srceen.match

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.webapp.thisweek.ui.common.PhonePreview
import com.webapp.thisweek.ui.theme.ThisWeekTheme
import com.webapp.thisweek.viewmodel.MatchViewModel
import com.webapp.thisweek.viewmodel.UserViewModel

@Composable
fun MatchTabScreen(
    userViewModel: UserViewModel = viewModel(),
    matchViewModel: MatchViewModel = viewModel()
) {
    // 매칭 프로세스가 진행될 동안
    // 소개팅 신청 -> 매칭 대기 -> 매칭 완료(프로필) 화면

    // 방법: 상태에 따라서 화면 선택 하는 방법 ( if, when )

    val myApp = userViewModel.currentUserId?.let {
        matchViewModel.findAppByUserId(it)
    }

    /// 화면 종류(분기)
    when {
        // - 소개팅 신청 화면
        myApp == null -> ApplyScreen(userViewModel, matchViewModel)

        // - 매칭 대기 화면
        myApp.matchId.isEmpty() -> WaitingScreen()

        // - 매칭 완료(프로필) 화면
        else -> {
            // 신청서는 있다.
            // 신청서에는 매치 아이디가 있다.
            myApp.matchId

            // 신청서에 있는 매치 아이디로 모든 신청서에서 상대방 신청서를 찾는다(내것이 아닌것)
            val partnerApp = matchViewModel.findPartnerApp(myApp)

            // 그 매칭 정보에는 남성, 여성 유저 아이디가 있다.
            // 그래서 이 정보에서 내 커런트 유저 아이디랑 다르면 이건 상대방이다.
            // 그리고 이 상대방 아이디로 유저 뷰모델에서 해당 상대방 유저 정보를 가져온다.

            val partner = partnerApp?.let {
                userViewModel.getUserById(it.userId)
            }

            if (partner == null) {
                Text("상대방 정보 불러오는 중...")
            } else {
                ProfileScreen(partner)
            }
        }
    }
}

@PhonePreview
@Composable
fun MatchTabScreenPreview() {
    ThisWeekTheme {
        MatchTabScreen()
    }
}









