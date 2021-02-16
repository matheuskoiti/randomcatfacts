package com.studiomk.randomcatfacts.data.repository

import com.google.gson.GsonBuilder
import com.studiomk.randomcatfacts.data.model.*
import com.studiomk.randomcatfacts.data.service.DogService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class DogRepository {
    private val dogApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(DogService::class.java)
    }

    private val dogFactsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://some-random-api.ml/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(DogService::class.java)
    }

    suspend fun getDogImages(): List<DogImage> {
        /**
         * Instead of a simple try/catch, I could create a wrapper class to handle and return
         * success, generic or specific error
         */
        return try {
            dogApiService.getRandomDogImage()
        } catch (e: Exception) {
            arrayListOf(DogImage(url = "https://cdn2.thedogapi.com/images/SJyBfg5NX_1280.jpg"))
        }
    }

    suspend fun getDogFact(): DogFact {
        /**
         * Instead of a simple try/catch, I could create a wrapper class to handle and return
         * success, generic or specific error
         */
        return try {
            dogFactsApi.getRandomDogFact()
        } catch (e: Exception) {
            DogFact("An error has occurred, please click Next Fact to try again")
        }
    }
}