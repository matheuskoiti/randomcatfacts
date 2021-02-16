package com.studiomk.randomcatfacts.presentation.viewModel

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.squareup.picasso.Picasso
import com.studiomk.randomcatfacts.data.repository.DogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogFactsViewModel(private val repository: DogRepository): ViewModel() {
    val factText = ObservableField<String>()
    val imageUrl = ObservableField<String>()
    val loading = ObservableField<String>()

    fun getFirstDogFact() = liveData(Dispatchers.IO) {
        val dogFact = repository.getDogFact()
        emit(dogFact)
        loading.set("stopLoading")
    }

    fun getFirstDogImage() = liveData(Dispatchers.IO) {
        val dogImage = repository.getDogImages()
        emit(dogImage)
    }

    fun onNextFactClick() {
        loading.set("startLoading")
        viewModelScope.launch {
            val dogFact= repository.getDogFact()
            factText.set(dogFact.fact)

            val dogImage = repository.getDogImages()
            imageUrl.set(dogImage.first().url)
            loading.set("stopLoading")
        }
    }

    companion object {
        @BindingAdapter("imgUrl")
        @JvmStatic
        fun setDogImage(imageView: ImageView?, imgUrl: String?) {
            Picasso.with(imageView?.context).load(imgUrl).into(imageView)
        }

        @BindingAdapter("loading")
        @JvmStatic
        fun setLoading(view: View?, loadingState: String?) {
            if (loadingState == "startLoading") {
                view?.visibility = View.VISIBLE
            } else {
                Handler(Looper.getMainLooper()).postDelayed({
                    view?.visibility = View.GONE
                }, 3000L)
            }
        }
    }
}