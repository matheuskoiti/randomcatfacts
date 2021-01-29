package com.studiomk.randomcatfacts.data.service

import com.studiomk.randomcatfacts.data.model.DogFact
import com.studiomk.randomcatfacts.data.model.DogImage
import retrofit2.http.GET

interface DogService {
    companion object {
        const val RANDOM_DOG_IMAGE_URL = "image/random"
        const val RANDOM_DOG_FACT_URL = "api/facts"
    }

    @GET(RANDOM_DOG_IMAGE_URL)
    suspend fun getRandomDogImage(): DogImage

    @GET(RANDOM_DOG_FACT_URL)
    suspend fun getRandomDogFact(): DogFact
}