package com.webapp.thisweek.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore
import com.webapp.thisweek.data.model.Message
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val db = Firebase.firestore

    private val _messageList = mutableStateListOf<Message>()
    val messageList: List<Message> = _messageList

    private var listener: ListenerRegistration? = null

    fun startListening(matchId: String) {
        stopListening()

        listener = db.collection("matches")
            .document(matchId)
            .collection("messages")
            .orderBy("createdAt")
            .addSnapshotListener { snapshots, exception ->
                if (snapshots == null) return@addSnapshotListener
                _messageList.clear()
                _messageList.addAll(snapshots.toObjects(Message::class.java))
            }
    }

    fun stopListening() {
        listener?.remove()
        listener = null
        _messageList.clear()
    }

    fun sendMessage(
        matchId: String,
        senderId: String,
        text: String,
        onFailure: (String) -> Unit = {},
        onSuccess: () -> Unit = {}
    ) {
        val message = Message(
            senderId = senderId,
            text = text
        )

        db.collection("matches")
            .document(matchId)
            .collection("messages")
            .add(message)
            .addOnSuccessListener { documentReference ->
                Log.d("ChatViewModel", "전송 성공: ${documentReference.id}")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("ChatViewModel", "전송 실패", e)
                onFailure(e.message ?: "전송 실패")
            }
    }

    fun delay() {
        viewModelScope.launch {
            test()
            test()
            test()
        }
        viewModelScope.launch {
            test2()
        }
    }

    suspend fun test() {
            Log.i("코루틴1", "1 시작")

            kotlinx.coroutines.delay(1000)

            Log.i("코루틴1", "1초 후")

            kotlinx.coroutines.delay(1000)

            Log.i("코루틴1", "2초 후")
    }

    suspend fun test2() {
        Log.i("코루틴2", "1 시작")

        kotlinx.coroutines.delay(1000)

        Log.i("코루틴2", "1초 후")

        kotlinx.coroutines.delay(1000)

        Log.i("코루틴2", "2초 후")
    }
}








