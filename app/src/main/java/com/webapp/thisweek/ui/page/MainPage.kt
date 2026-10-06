package com.webapp.thisweek.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import com.webapp.thisweek.ui.navigation.BottomRouter
import com.webapp.thisweek.ui.srceen.ProfileScreen

@Composable
fun MainPage() {
    val tabs = listOf(BottomRouter.MatchTab, BottomRouter.ChatTab, BottomRouter.MyPageTab)
    var currentTab: BottomRouter by remember { mutableStateOf(BottomRouter.MatchTab) }

    Scaffold(
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
                BottomRouter.MatchTab -> ProfileScreen()
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