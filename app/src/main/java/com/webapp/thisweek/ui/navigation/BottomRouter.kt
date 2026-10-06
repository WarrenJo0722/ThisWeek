package com.webapp.thisweek.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomRouter(
    val label: String,
    val icon: ImageVector,
    val desc: String,
) {
    object MatchTab : BottomRouter(
        label = "매칭",
        icon = Icons.Default.DateRange,
        desc = "매칭 탭"
    )
    object ChatTab : BottomRouter(
        label = "대화",
        icon = Icons.Default.Email,
        desc = "대화 탭"
    )
    object MyPageTab : BottomRouter(
        label = "마이",
        icon = Icons.Default.Person,
        desc = "마이페이지 탭"
    )
}




