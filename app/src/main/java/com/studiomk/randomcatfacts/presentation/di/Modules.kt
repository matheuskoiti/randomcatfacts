package com.studiomk.randomcatfacts.presentation.di

import com.studiomk.randomcatfacts.data.repository.CatRepository
import com.studiomk.randomcatfacts.data.repository.DogRepository
import com.studiomk.randomcatfacts.data.repository.PandaRepository
import com.studiomk.randomcatfacts.presentation.viewModel.CatFactsViewModel
import com.studiomk.randomcatfacts.presentation.viewModel.DogFactsViewModel
import com.studiomk.randomcatfacts.presentation.viewModel.PandaFactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module(override = true) {
    single { CatRepository() }
    single { DogRepository() }
    single { PandaRepository() }
    viewModel { CatFactsViewModel(get()) }
    viewModel { DogFactsViewModel(get()) }
    viewModel { PandaFactsViewModel(get()) }
}