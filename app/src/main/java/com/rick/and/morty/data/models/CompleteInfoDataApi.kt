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
    @SerializedName("type") val type: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("origin") val origin: OriginDataApi?,
    @SerializedName("location") val location: LocationDataApi?,
    @SerializedName("image") val image: String?,
    @SerializedName("episode") val episode: List<String>?,
    @SerializedName("url") val url: String?,
    @SerializedName("created") val created: String?
)

data class OriginDataApi(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)

data class LocationDataApi(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)
