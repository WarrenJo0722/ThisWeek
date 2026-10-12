package com.webapp.thisweek.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webapp.thisweek.ui.navigation.BottomRouter
import com.webapp.thisweek.ui.srceen.match.MatchTabScreen
import com.webapp.thisweek.ui.srceen.mypage.MyPageScreen
import com.webapp.thisweek.viewmodel.MatchViewModel
import com.webapp.thisweek.viewmodel.UserViewModel

@Composable
fun MainPage(
    navController: NavController = rememberNavController(),
    userViewModel: UserViewModel = viewModel(),
    matchViewModel: MatchViewModel = viewModel()
) {
    val tabs = listOf(BottomRouter.MatchTab, BottomRouter.ChatTab, BottomRouter.MyPageTab)
    var currentTab: BottomRouter by remember { mutableStateOf(BottomRouter.MatchTab) }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            NavigationBar() {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = currentTab == tab,
                        onClick = { currentTab = tab},
                        icon = { Icon(tab.icon, tab.desc) },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            Modifier.padding(innerPadding)
        ) {
            when(currentTab) {
                BottomRouter.MatchTab -> MatchTabScreen(navController, userViewModel, matchViewModel)
                BottomRouter.MyPageTab -> MyPageScreen(userViewModel) {
                    navController.navigate("intro") {
                        popUpTo("main") { inclusive = true }
                    }
                }
                else -> Text("페이지 구현중")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPagePreview() {
    MainPage()
}