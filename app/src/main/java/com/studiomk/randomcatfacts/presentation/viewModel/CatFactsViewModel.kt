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
import com.studiomk.randomcatfacts.data.model.CatFact
import com.studiomk.randomcatfacts.data.model.FactStatus
import com.studiomk.randomcatfacts.data.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatFactsViewModel(private val repository: CatRepository) : ViewModel() {
    val factText = ObservableField<String>()
    val imageUrl = ObservableField<String>()
    val loading = ObservableField<String>()

    /**
     * I could just call factsViewModel.onNextFactClick() but I kept this way for study purposes
     */
    fun getFirstCatImage() = liveData(Dispatchers.IO) {
        val catImage = repository.getCatImages()
        emit(catImage)
        loading.set("stopLoading")
    }

    /**
     * I could just call factsViewModel.onNextFactClick() but I kept this way for study purposes
     */
    fun getFirstCatFact() = liveData(Dispatchers.IO) {
        var catFact: CatFact
        do {
            catFact = repository.getCatFact()
        } while (!catFact.status.verified)
        emit(catFact)
    }

    fun onNextFactClick() {
        loading.set("startLoading")
        viewModelScope.launch {
            var catFact: CatFact
            do {
                catFact = repository.getCatFact()
            } while (!catFact.status.verified)
            factText.set(catFact.text)

            val catImage = repository.getCatImages()
            imageUrl.set(catImage.first().url)
            loading.set("stopLoading")
        }
    }

    companion object {
        @BindingAdapter("imgUrl")
        @JvmStatic
        fun setProfilePicture(imageView: ImageView?, imgUrl: String?) {
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