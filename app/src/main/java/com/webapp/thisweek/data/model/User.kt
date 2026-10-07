package com.webapp.thisweek.data.model

import com.google.firebase.firestore.DocumentId

data class User(
    @DocumentId
    val id: String = "",
    val nick: String = "",
    val email: String = "",
    val password: String = "",
)
