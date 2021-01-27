package com.studiomk.randomcatfacts.presentation.viewModel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.squareup.picasso.Picasso
import com.studiomk.randomcatfacts.data.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatFactsViewModel : ViewModel() {
    private val repository = CatRepository()
    val factText = ObservableField<String>()
    val imageUrl = ObservableField<String>()

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

            val catImage = repository.getCatImages()
            imageUrl.set(catImage.first().url)
        }
    }

    companion object {
        @BindingAdapter("imgUrl")
        @JvmStatic
        fun setProfilePicture(imageView: ImageView?, imgUrl: String?) {
            Picasso.with(imageView?.context).load(imgUrl).into(imageView)
        }
    }
}