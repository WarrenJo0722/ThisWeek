package com.webapp.thisweek.ui.srceen.match

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.webapp.thisweek.data.model.Place
import com.webapp.thisweek.data.model.Weekday
import com.webapp.thisweek.ui.common.PhonePreview
import com.webapp.thisweek.ui.common.PrimaryButton
import com.webapp.thisweek.ui.common.VerticalSpacer
import com.webapp.thisweek.ui.theme.Serif
import com.webapp.thisweek.ui.theme.ThisWeekTheme
import com.webapp.thisweek.viewmodel.MatchViewModel
import com.webapp.thisweek.viewmodel.UserViewModel
import java.nio.file.WatchEvent

@Composable
fun ApplyScreen(
    userViewModel: UserViewModel = viewModel(),
    matchViewModel: MatchViewModel = viewModel()
) {
    val context = LocalContext.current

    val places = Place.entries
    val weekdays = Weekday.entries

    var selectedPlace by remember { mutableStateOf(Place.GANG) }
    var selectedWeekday by remember { mutableStateOf(Weekday.SATURDAY) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = """
            이번 주,
            소개팅
            나가보실래요?
        """.trimIndent(),
            fontFamily = Serif,
            fontSize = 28.sp,
            fontWeight = FontWeight.Black
        )

        VerticalSpacer()

        Text(
            text = """
            인년이 아직 누군지는 몰라요.
            매칭전문가 민지가 운명의 상대를 추천해드려요.
        """.trimIndent(),
            color = Color.Gray
        )

        VerticalSpacer(24)

        Text("어디서 만날까요?")

        places.forEach { place ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedPlace == place,
                    onClick = { selectedPlace = place }
                )
                Text(place.label)
            }
        }

        VerticalSpacer(24)

        Text("언제가 좋아요?")

        weekdays.forEach { weekday ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedWeekday == weekday,
                    onClick = { selectedWeekday = weekday }
                )
                Text(weekday.label)
            }
        }


        Spacer(Modifier.weight(1.0f))

        PrimaryButton("이번 주 신청하기") {
            matchViewModel.apply(
                userId = userViewModel.currentUserId ?: "",
                place = selectedPlace,
                weekday = selectedWeekday
            ) {
                Toast.makeText(context, "회원이 생성되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@PhonePreview
@Composable
fun ApplyScreenPreview() {
    ThisWeekTheme {
        ApplyScreen()
    }
}









