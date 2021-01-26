package com.studiomk.randomcatfacts.presentation.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.studiomk.randomcatfacts.data.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatFactsViewModel : ViewModel() {
    private val repository = CatRepository()
    val factText = ObservableField<String>()

    fun getCatImage() = liveData(Dispatchers.IO) {
        val catImage = repository.getCatImages()
        emit(catImage)
    }

    fun getCatFact() = liveData(Dispatchers.IO) {
        val catFact = repository.getCatFact()
        emit(catFact)
    }

    fun onNextFactClick() {
        viewModelScope.launch {
            val catFact = repository.getCatFact()
            factText.set(catFact.text)
        }
    }
}