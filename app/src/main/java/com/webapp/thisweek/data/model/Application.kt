package com.webapp.thisweek.data.model

import com.google.firebase.firestore.DocumentId

data class Application(
    @DocumentId
    val id: String = "",
    val userId: String = "",
    val week: Int = 0,
    val place: Place = Place.GANG,
    val weekday: Weekday = Weekday.SATURDAY,
)
