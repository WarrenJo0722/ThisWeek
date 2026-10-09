package com.webapp.thisweek.data.model

import com.google.firebase.firestore.DocumentId

data class Application(
    @DocumentId
    val id: String = "",
    val userId: String = "",
    val week: Int = 0,
    val place: Place = Place.GANG,
    val weekday: Weekday = Weekday.SATURDAY,
    val matchId: String = "", // 비어 있으면 매칭 전, 값이 있으면 매칭 완료
)
