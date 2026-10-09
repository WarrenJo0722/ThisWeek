package com.webapp.thisweek.data.model

import com.google.firebase.firestore.DocumentId

data class Match(
    @DocumentId
    val id: String = "",
    val week: Int = 0,
    val user1id: String = "", // 남성
    val user2id: String = "", // 여성
    val place: Place = Place.GANG,
    val weekday: Weekday = Weekday.SATURDAY,
)
