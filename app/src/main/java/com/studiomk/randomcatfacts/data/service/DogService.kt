package com.studiomk.randomcatfacts.data.service

import com.studiomk.randomcatfacts.data.model.DogFact
import com.studiomk.randomcatfacts.data.model.DogImage
import retrofit2.http.GET

interface DogService {
    companion object {
        const val RANDOM_DOG_IMAGE_URL = "v1/images/search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=1"
        const val RANDOM_DOG_FACT_URL = "facts/dog"
    }

    @GET(RANDOM_DOG_IMAGE_URL)
    suspend fun getRandomDogImage(): List<DogImage>

    @GET(RANDOM_DOG_FACT_URL)
    suspend fun getRandomDogFact(): DogFact
}