package com.studiomk.randomcatfacts.presentation.di

import com.studiomk.randomcatfacts.data.repository.CatRepository
import com.studiomk.randomcatfacts.data.repository.DogRepository
import com.studiomk.randomcatfacts.presentation.viewModel.CatFactsViewModel
import com.studiomk.randomcatfacts.presentation.viewModel.DogFactsViewModel
import org.koin.dsl.module

val applicationModule = module(override = true) {
    single { CatRepository() }
    single { DogRepository() }
    single { CatFactsViewModel(get()) }
    single { DogFactsViewModel(get()) }
}