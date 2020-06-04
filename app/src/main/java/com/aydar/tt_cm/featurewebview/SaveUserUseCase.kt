package com.aydar.tt_cm.featurewebview

import com.aydar.tt_cm.data.model.User
import com.aydar.tt_cm.data.repository.UserRepository

class SaveUserUseCase(private val userRepository: UserRepository) {

    suspend fun invoke(user: User) {
        userRepository.saveUserWithLink(user)
    }
}