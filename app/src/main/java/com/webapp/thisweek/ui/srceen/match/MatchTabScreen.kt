package com.webapp.thisweek.ui.srceen.match

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

    var isApplied by remember { mutableStateOf(false) }
    var isMatched by remember { mutableStateOf(false) }

    /// 화면 종류(분기)
    when (isApplied) {
        // - 소개팅 신청 화면
        false -> ApplyScreen(userViewModel, matchViewModel) {
            isApplied = true
        }

        true -> {
            if (!isMatched) {
                // - 매칭 대기 화면
                WaitingScreen()
            } else {
                // - 매칭 완료(프로필) 화면
                ProfileScreen()
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









