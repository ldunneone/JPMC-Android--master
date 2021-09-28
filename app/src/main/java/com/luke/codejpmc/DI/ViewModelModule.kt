package com.luke.codejpmc.DI

import com.luke.codejpmc.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { MainViewModel(get()) }
}