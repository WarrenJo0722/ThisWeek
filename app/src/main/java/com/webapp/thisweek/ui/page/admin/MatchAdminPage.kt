package com.webapp.thisweek.ui.page.admin

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.webapp.thisweek.data.model.Application
import com.webapp.thisweek.data.model.Gender
import com.webapp.thisweek.ui.common.ColumnScaffold
import com.webapp.thisweek.ui.common.PrimaryButton
import com.webapp.thisweek.viewmodel.MatchViewModel
import com.webapp.thisweek.viewmodel.UserViewModel

// 남성 아이디
// 여성 아이디
// 몇 주차


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchAdminScreen(
    userViewModel: UserViewModel = viewModel(),
    matchViewModel: MatchViewModel = viewModel()
) {
    val context = LocalContext.current

    val applicationList = matchViewModel.applicationList

    var maleApplication by remember { mutableStateOf<Application?>(null) }
    var femaleApplication by remember { mutableStateOf<Application?>(null) }

    var detailApplication by remember { mutableStateOf<Application?>(null) }

    ColumnScaffold() {
        applicationList.forEach { application ->
            val user = userViewModel.getUserById(application.userId)

            if (user == null) return@forEach

            ListItem(
                headlineContent = { Text(user.nick) },
                supportingContent = { Text("${user.gender.label} / ${user.age} · ${application.place.label} · ${application.weekday.label}") },
                trailingContent = {
                    if (application.matchId.isNotEmpty()) {
                        Text("매칭 완료")
                    }
                    if (maleApplication?.id == application.id || femaleApplication?.id == application.id) {
                        Icon(Icons.Default.Check, contentDescription = null)
                    }
                },
                modifier = Modifier.combinedClickable(
                    onClick = {
                        if (application.matchId.isNotEmpty()) return@combinedClickable

                        if (user.gender == Gender.MALE) {
                            maleApplication = application
                        } else {
                            femaleApplication = application
                        }
                    },
                    onLongClick = {
                        detailApplication = application
                    }
                ),
                colors = ListItemDefaults.colors(containerColor = if (application.matchId.isEmpty()) Color.White else Color.Gray)
            )
            HorizontalDivider()
        }

        PrimaryButton("매칭하기") {
            val male = maleApplication
            val female = femaleApplication

            if (male != null && female != null) {
                matchViewModel.match(male, female) {
                    maleApplication = null
                    femaleApplication = null

                    Toast.makeText(
                        context,
                        "매칭이 완료되었습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    context,
                    "남성, 여성, 모두 선택해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    detailApplication?.let { application ->
        val user = userViewModel.getUserById(application.userId)

        AlertDialog(
            onDismissRequest = {
                detailApplication = null
            },
            /*properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
            ),*/
            title = {
                Text(user?.nick ?: "알 수 없는 사용자")
            },
            text = {
                Text("""
                    성별: ${user?.gender?.label}
                    나이: ${user?.age}
                    직업: ${user?.job}
                    MBTI: ${user?.mbti}
                    한 줄 소개: ${user?.greeting}
                    
                    장소: ${application.place}
                    요일: ${application.weekday}
                """.trimIndent())
            },
            confirmButton = {
                TextButton(onClick = { detailApplication = null }) {
                    Text("닫기")
                }
            }
        )
    }
}
















