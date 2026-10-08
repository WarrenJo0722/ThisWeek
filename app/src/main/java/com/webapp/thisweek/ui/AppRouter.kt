package com.webapp.thisweek.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.webapp.thisweek.ui.page.admin.AdminPage
import com.webapp.thisweek.ui.page.IntroPage
import com.webapp.thisweek.ui.page.MainPage
import com.webapp.thisweek.ui.page.admin.MatchAdminScreen
import com.webapp.thisweek.ui.page.admin.RegisterPage
import com.webapp.thisweek.ui.page.admin.UserListPage
import com.webapp.thisweek.viewmodel.MatchViewModel
import com.webapp.thisweek.viewmodel.UserViewModel

@Composable
fun AppRouter() {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()
    val matchViewModel: MatchViewModel = viewModel()

    // DI : 디펜던시 인젝션(의존성 주입)
    // 1. 생성자 주입
    // 2. 필드 주입
    // 3. 세터 주입

    // 생성자 주입, 자동 주입

    NavHost(navController, startDestination = "intro") {
        composable("intro") {
            IntroPage(navController = navController)
        }
        composable("admin") {
            AdminPage(navController = navController)
        }
        composable("register") {
            RegisterPage(userViewModel)
        }
        composable("userList") {
            UserListPage(userViewModel)
        }
        composable("matchAdmin") {
            MatchAdminScreen(matchViewModel)
        }
        composable("main") {
            MainPage(userViewModel, matchViewModel)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppRouterPreview() {
    AppRouter()
}





