package com.rick.and.morty.data.api

import com.rick.and.morty.data.models.CompleteInfoDataApi
import retrofit2.http.*


interface  RickAndMortyApiClient {


    @Headers("Content-Type: application/json")
    @GET("character/?")
   suspend fun getAnimationCharacterList(
        @Query("page") page: Int
    ): CompleteInfoDataApi

}

