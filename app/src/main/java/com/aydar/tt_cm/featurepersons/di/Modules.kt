package com.aydar.tt_cm.featurepersons.di

import com.aydar.tt_cm.featurepersons.domain.ShowPersonsUseCase
import com.aydar.tt_cm.featurepersons.presentation.PersonsViewModel
import org.koin.dsl.module

val featurePersonsModule = module{

    factory { ShowPersonsUseCase(get()) }
    factory { PersonsViewModel(get()) }
}