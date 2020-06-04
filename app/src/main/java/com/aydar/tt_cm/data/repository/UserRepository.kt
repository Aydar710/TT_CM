package com.aydar.tt_cm.data.repository

import com.aydar.tt_cm.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(private val db: FirebaseFirestore) {

    suspend fun saveUserWithLink(user: User) {
        if (user.link == null) {
            throw Throwable("User has no link")
        } else {
            db.collection(COLLECTION_USERS)
                .document(user.id)
                .set(user)
                .await()
        }
    }

    suspend fun getUserById(id: String): User? {
        val userSnapshot = db.collection(COLLECTION_USERS)
            .document(id)
            .get()
            .await()

        val user = userSnapshot.toObject(User::class.java)
        return user
    }
}