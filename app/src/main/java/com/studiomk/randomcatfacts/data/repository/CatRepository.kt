package com.studiomk.randomcatfacts.data.repository

import com.google.gson.GsonBuilder
import com.studiomk.randomcatfacts.data.model.CatFact
import com.studiomk.randomcatfacts.data.model.CatImage
import com.studiomk.randomcatfacts.data.model.FactStatus
import com.studiomk.randomcatfacts.data.service.CatService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class CatRepository {

    private val theCatApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(CatService::class.java)
    }

    private val catFactService by lazy {
        Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(CatService::class.java)
    }

    suspend fun getCatImages(): List<CatImage> {
        /**
         * Instead of a simple try/catch, I could create a wrapper class to handle and return
         * success, generic or specific error
         */
        return try {
            theCatApiService.getRandomCatImage()
        } catch (e: Exception) {
            arrayListOf(CatImage(url = "https://cdn2.thecatapi.com/images/293.jpg",
                width = "429", height = "500"))
        }
    }

    suspend fun getCatFact(): CatFact {
        /**
         * Instead of a simple try/catch, I could create a wrapper class to handle and return
         * success, generic or specific error
         */
        return try {
            catFactService.getRandomCatFact()
        } catch (e: Exception) {
            CatFact("An error has occurred, please click Next Fact to try again", FactStatus(true))
        }
    }
}