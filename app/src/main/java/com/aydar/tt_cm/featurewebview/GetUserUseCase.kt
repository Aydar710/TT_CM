package com.aydar.tt_cm.featurewebview

import com.aydar.tt_cm.data.model.User
import com.aydar.tt_cm.data.repository.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {

    suspend fun invoke(id: String): User? {
        return userRepository.getUserById(id)
    }
}