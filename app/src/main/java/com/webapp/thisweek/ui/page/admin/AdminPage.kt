package com.webapp.thisweek.ui.page.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webapp.thisweek.ui.common.PhonePreview

@Composable
fun AdminPage(navController: NavController = rememberNavController()) {
    Scaffold(
        containerColor = Color.White,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            ListItem(
                headlineContent = { Text("회원 생성") },
                trailingContent = {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                },
                modifier = Modifier.clickable {
                    navController.navigate("register")
                },
                colors = ListItemDefaults.colors(containerColor = Color.White)
            )
            HorizontalDivider()

            ListItem(
                headlineContent = { Text("회원 목록") },
                trailingContent = {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                },
                modifier = Modifier.clickable {
                    navController.navigate("userList")
                },
                colors = ListItemDefaults.colors(containerColor = Color.White)
            )
            HorizontalDivider()

            ListItem(
                headlineContent = { Text("매칭 하기") },
                trailingContent = {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                },
                modifier = Modifier.clickable {
                    navController.navigate("matchAdmin")
                },
                colors = ListItemDefaults.colors(containerColor = Color.White)
            )
            HorizontalDivider()
        }
    }
}

@PhonePreview
@Composable
fun AdminPagePreview() {
    AdminPage()
}