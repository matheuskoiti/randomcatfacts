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
import com.studiomk.randomcatfacts.data.repository.FoxRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoxFactsViewModel(private val repository: FoxRepository): ViewModel() {
    val factText = ObservableField<String>()
    val imageUrl = ObservableField<String>()
    val loading = ObservableField<String>()

    fun getFirstFoxFact() = liveData(Dispatchers.IO) {
        val dogFact = repository.getFoxFact()
        emit(dogFact)
        loading.set("stopLoading")
    }

    fun getFirstFoxImage() = liveData(Dispatchers.IO) {
        val dogImage = repository.getFoxImage()
        emit(dogImage)
    }

    fun onNextFactClick() {
        loading.set("startLoading")
        viewModelScope.launch {
            val fact= repository.getFoxFact()
            factText.set(fact.fact)

            val image = repository.getFoxImage()
            imageUrl.set(image.link)
            loading.set("stopLoading")
        }
    }

    companion object {
        @BindingAdapter("imgUrl")
        @JvmStatic
        fun setFoxImage(imageView: ImageView?, imgUrl: String?) {
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