package com.rick.and.morty.data.models

import com.google.gson.annotations.SerializedName

data class CompleteInfoDataApi(
    @SerializedName("results") val result: List<AnimationCharacterDataApi>?,
)

data class AnimationCharacterDataApi(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("species") val species: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("image") val image: String?,
)
