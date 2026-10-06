package com.webapp.thisweek.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.webapp.thisweek.ui.page.IntroPage
import com.webapp.thisweek.ui.page.MainPage

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "intro") {
        composable("intro") {
            IntroPage(navController = navController)
        }
        composable("main") {
            MainPage()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppRouterPreview() {
    AppRouter()
}





