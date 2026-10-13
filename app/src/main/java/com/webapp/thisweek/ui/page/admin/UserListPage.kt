package com.webapp.thisweek.ui.page.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.webapp.thisweek.ui.common.ColumnScaffold
import com.webapp.thisweek.ui.common.PhonePreview
import com.webapp.thisweek.viewmodel.UserViewModel

@Composable
fun UserListPage(userViewModel: UserViewModel = viewModel()) {
    val userList = userViewModel.userList
    val currentUser = userViewModel.currentUser

    LaunchedEffect("") {
        userViewModel.loadUsers()
    }

    ColumnScaffold() {
        userList.forEach { user ->
            ListItem(
                headlineContent = { Text(user.nick) },
                supportingContent = { Text("${user.gender.label} / ${user.age}") },
                trailingContent = {
                    if (currentUser?.id == user.id) {
                        Icon(Icons.Default.Check, contentDescription = null)
                    }
                },
                modifier = Modifier.clickable {
                    userViewModel.selectUser(user)
                },
                colors = ListItemDefaults.colors(containerColor = Color.White)
            )
            HorizontalDivider()
        }
    }
}

@PhonePreview
@Composable
fun UserListPagePreview() {
    UserListPage()
}









