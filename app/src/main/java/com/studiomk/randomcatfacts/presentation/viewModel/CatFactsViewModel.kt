package com.studiomk.randomcatfacts.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.studiomk.randomcatfacts.data.repository.CatRepository
import kotlinx.coroutines.Dispatchers

class CatFactsViewModel : ViewModel() {

    private val repository = CatRepository()

    fun getCatImage() = liveData(Dispatchers.IO) {
        val catImage = repository.getCatImages()
        emit(catImage)
    }

    fun onNextFactClick() {
        
    }


}