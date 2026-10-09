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
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.webapp.thisweek.ui.common.VerticalSpacer
import com.webapp.thisweek.ui.page.admin.RegisterPage

@Composable
fun SignUpPage(userViewModel: UserViewModel = viewModel()) {
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
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = user.nick,
                onValueChange = { user = user.copy(nick = it) },
                label = { Text("닉네임") },
                modifier = Modifier.fillMaxWidth()
            )
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

            OutlinedTextField(
                value = if (user.age == 0) "" else user.age.toString(),
                onValueChange = { user = user.copy(age = it.toIntOrNull() ?: 0) },
                label = { Text("나이") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            // 성별
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("성별")
                Gender.entries.forEach { gender ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = user.gender == gender,
                            onClick = { user = user.copy(gender = gender) }
                        )
                        Text(gender.label)
                    }
                }
            }

            // 직업
            OutlinedTextField(
                value = user.job,
                onValueChange = { user = user.copy(job = it) },
                label = { Text("직업") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = user.mbti,
                onValueChange = { user = user.copy(mbti = it) },
                label = { Text("MBTI") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = user.greeting,
                onValueChange = { user = user.copy(greeting = it) },
                label = { Text("한 줄 소개") },
                modifier = Modifier.fillMaxWidth()
            )

            VerticalSpacer()

            PrimaryButton("회원가입") {
                userViewModel.signUp(user)/* {
                    Toast.makeText(context, "회원이 생성되었습니다.", Toast.LENGTH_SHORT).show()

                    user = User()
                }*/
            }
        }
    }
}

@PhonePreview
@Composable
fun SignUpPagePreview() {
    SignUpPage()
}