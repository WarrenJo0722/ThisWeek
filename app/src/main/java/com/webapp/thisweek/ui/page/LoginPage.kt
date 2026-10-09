package com.webapp.thisweek.ui.page

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.webapp.thisweek.data.model.Gender
import com.webapp.thisweek.data.model.User
import com.webapp.thisweek.ui.common.PhonePreview
import com.webapp.thisweek.ui.common.PrimaryButton
import com.webapp.thisweek.viewmodel.UserViewModel
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webapp.thisweek.ui.common.VerticalSpacer
import com.webapp.thisweek.ui.page.admin.RegisterPage

@Composable
fun LoginPage(
    navController: NavController = rememberNavController(),
    userViewModel: UserViewModel = viewModel()
) {
    val context = LocalContext.current

    var user by remember { mutableStateOf(User()) }

    var showPassword by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(32.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = user.email,
                onValueChange = { user = user.copy(email = it) },
                label = { Text("이메일") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = user.password,
                onValueChange = { user = user.copy(password = it) },
                label = { Text("비밀번호") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (showPassword)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = {
                        showPassword = !showPassword
                    }) {
                        if (showPassword) {
                            Icon(Icons.Default.VisibilityOff, contentDescription = null)
                        } else {
                            Icon(Icons.Default.Visibility, contentDescription = null)
                        }

                    }
                }
            )

            VerticalSpacer()

            PrimaryButton("로그인") {
                userViewModel.addUser(user) {
                    Toast.makeText(context, "회원이 생성되었습니다.", Toast.LENGTH_SHORT).show()

                    user = User()
                }
            }

            TextButton(onClick = {
                navController.navigate("signUp")
            }) {
                Text("회원가입")
            }
        }
    }
}

@PhonePreview
@Composable
fun LoginPagePreview() {
    LoginPage()
}