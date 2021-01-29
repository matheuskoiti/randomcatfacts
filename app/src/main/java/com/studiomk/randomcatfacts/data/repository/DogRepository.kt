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
            .baseUrl("https://dog-api.kinduff.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(DogService::class.java)
    }

    private val dogCeoService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(DogService::class.java)
    }

    suspend fun getDogImages(): DogImage {
        /**
         * Instead of a simple try/catch, I could create a wrapper class to handle and return
         * success, generic or specific error
         */
        return try {
            dogCeoService.getRandomDogImage()
        } catch (e: Exception) {
            DogImage(message = "https://cdn2.thecatapi.com/images/293.jpg")
        }
    }

    suspend fun getDogFact(): DogFact {
        /**
         * Instead of a simple try/catch, I could create a wrapper class to handle and return
         * success, generic or specific error
         */
        return try {
            dogApiService.getRandomDogFact()
        } catch (e: Exception) {
            DogFact(facts = arrayListOf("An error has occurred, please click Next Fact to try again"))
        }
    }
}