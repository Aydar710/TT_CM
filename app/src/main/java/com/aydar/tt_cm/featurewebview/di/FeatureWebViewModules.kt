package com.aydar.tt_cm.featurewebview.di

import com.aydar.tt_cm.featurewebview.GetUserUseCase
import com.aydar.tt_cm.featurewebview.SaveUserUseCase
import com.aydar.tt_cm.featurewebview.WebViewViewModel
import org.koin.dsl.module

val featureWebViewModule = module {

    factory { SaveUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { WebViewViewModel(get(), get()) }
}