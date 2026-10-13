package com.webapp.thisweek.ui.page

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.webapp.thisweek.ui.common.LoadingDialog
import com.webapp.thisweek.ui.common.VerticalSpacer
import com.webapp.thisweek.ui.page.admin.RegisterPage

@Composable
fun SignUpPage(
    navController: NavController = rememberNavController(),
    userViewModel: UserViewModel = viewModel()
) {
    val context = LocalContext.current

    var user by remember { mutableStateOf(User()) }

    var showPassword by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // 인텐트 : 화면 이동에 필요한 정보
        // - 코드 : 어떤 처리를 할 수 있는 화면을 불러줄까? ex > image, video, sms, call

    // 런처 : 다른 화면으로 넘어가거나 다시 우리 화면으로 돌아오는 동작을 수행해주는 녀석

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            imageUri = uri
        }
    }

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
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,

                // 불러오는 중에
                placeholder = rememberVectorPainter(Icons.Default.Person),

                // 실패했을 때
                error = rememberVectorPainter(Icons.Default.Person),

                // 이미지 uri가 널일때
                fallback = rememberVectorPainter(Icons.Default.Person),

                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .clickable {
                        launcher
                            .launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                    }
            )

            VerticalSpacer()

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
                isLoading = true

                userViewModel.signUp(
                    user = user,
                    imageUri = imageUri,
                    onFailure = {
                        isLoading = false
                    }
                ) {
                    isLoading = false

                    Toast.makeText(context, "회원가입 완료!", Toast.LENGTH_SHORT).show()

                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
        }
    }

    if (isLoading) {
        LoadingDialog()
    }
}

@PhonePreview
@Composable
fun SignUpPagePreview() {
    SignUpPage()
}