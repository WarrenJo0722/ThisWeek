package com.webapp.thisweek.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webapp.thisweek.ui.common.PhonePreview
import com.webapp.thisweek.ui.common.PrimaryButton

@Composable
fun AdminPage(navController: NavController = rememberNavController()) {
    Scaffold(
        containerColor = Color.White,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            PrimaryButton("회원 생성") {
                navController.navigate("register")
            }
        }
    }
}

@PhonePreview
@Composable
fun AdminPagePreview() {
    AdminPage()
}