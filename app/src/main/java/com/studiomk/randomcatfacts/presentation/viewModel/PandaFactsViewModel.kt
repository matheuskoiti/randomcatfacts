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
import com.studiomk.randomcatfacts.data.repository.PandaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PandaFactsViewModel(private val repository: PandaRepository): ViewModel() {
    val factText = ObservableField<String>()
    val imageUrl = ObservableField<String>()
    val loading = ObservableField<String>()

    fun getFirstPandaFact() = liveData(Dispatchers.IO) {
        val dogFact = repository.getPandaFact()
        emit(dogFact)
        loading.set("stopLoading")
    }

    fun getFirstPandaImage() = liveData(Dispatchers.IO) {
        val dogImage = repository.getPandaImage()
        emit(dogImage)
    }

    fun onNextFactClick() {
        loading.set("startLoading")
        viewModelScope.launch {
            val dogFact= repository.getPandaFact()
            factText.set(dogFact.fact)

            val dogImage = repository.getPandaImage()
            imageUrl.set(dogImage.link)
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