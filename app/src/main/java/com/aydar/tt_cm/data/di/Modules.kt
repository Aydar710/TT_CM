package com.aydar.tt_cm.data.di

import com.aydar.tt_cm.data.repository.PersonsRepository
import com.aydar.tt_cm.data.repository.UserRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val repositoryModule = module {
    single { Firebase.firestore }

    factory { PersonsRepository() }
    factory { UserRepository(get()) }

}