package com.webapp.thisweek.data.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude

data class User(
    @DocumentId
    val id: String = "",
    val nick: String = "",
    val email: String = "",
    @get:Exclude
    val password: String = "",
    val age: Int = 0,
    val gender: Gender = Gender.MALE,
    val job: String = "",
    val mbti: String = "",
    val greeting: String = "",
    val imageUrl: String = ""
)

enum class Gender(val label: String) {
    MALE("남성"),
    FEMALE("여성")
}






