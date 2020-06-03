package com.aydar.tt_cm.data.di

import com.aydar.tt_cm.data.repository.PersonsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { PersonsRepository() }
}