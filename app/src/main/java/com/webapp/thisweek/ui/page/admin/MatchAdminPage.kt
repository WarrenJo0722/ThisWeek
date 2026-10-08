package com.webapp.thisweek.ui.page.admin

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.webapp.thisweek.ui.common.ColumnScaffold
import com.webapp.thisweek.ui.common.PrimaryButton
import com.webapp.thisweek.viewmodel.MatchViewModel

// 남성 아이디
// 여성 아이디
// 몇 주차


@Composable
fun MatchAdminScreen(matchViewModel: MatchViewModel = viewModel()) {
    val applicationList = matchViewModel.applicationList

    var male by remember { mutableStateOf("") }
    var female by remember { mutableStateOf("") }

    ColumnScaffold() {
        applicationList.forEach { application ->
            ListItem(
                headlineContent = { Text(application.userId) },
//                supportingContent = { Text("${user.gender.label} / ${user.age}") },
                trailingContent = {
                    if (male == application.userId || female == application.userId) {
                        Icon(Icons.Default.Check, contentDescription = null)
                    }
                },
                modifier = Modifier.clickable {
                    male = application.userId
                },
                colors = ListItemDefaults.colors(containerColor = Color.White)
            )
            HorizontalDivider()
        }

        PrimaryButton("매칭하기")
    }
}