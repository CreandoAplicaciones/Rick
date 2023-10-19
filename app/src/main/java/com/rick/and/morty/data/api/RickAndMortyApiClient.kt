package com.rick.and.morty.data.api

import com.rick.and.morty.data.models.AnimationCharacterDataApi
import com.rick.and.morty.data.models.AnimationCharacterDetailDataApi
import com.rick.and.morty.data.models.CompleteInfoDataApi
import retrofit2.http.*


interface  RickAndMortyApiClient {


    @Headers("Content-Type: application/json")
    @GET("character/?")
   suspend fun getAnimationCharacterList(
        @Query("page") page: Int
    ): CompleteInfoDataApi

    @Headers("Content-Type: application/json")
    @GET("character/{id}")
    suspend fun getAnimationCharacter(
        @Path("id") id: Int
    ): AnimationCharacterDetailDataApi

}

