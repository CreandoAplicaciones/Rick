package com.rick.and.morty.data.mapper

import com.rick.and.morty.data.base.Mapper
import com.rick.and.morty.data.models.AnimationCharacterDataApi
import com.rick.and.morty.data.models.AnimationCharacterDetailDataApi
import com.rick.and.morty.data.models.LocationDataApi
import com.rick.and.morty.data.models.OriginDataApi
import com.rick.and.morty.domain.model.AnimationCharacter
import com.rick.and.morty.domain.model.AnimationCharacterDetail

class AnimationCharacterDetailApiMapper: Mapper<AnimationCharacterDetailDataApi, AnimationCharacterDetail> {
    override fun map(model: AnimationCharacterDetailDataApi): AnimationCharacterDetail {
        var list = if (model.episode.isNullOrEmpty()) {
            listOf("")
        } else {
            model.episode
        }
        return AnimationCharacterDetail(
            id = model.id?: 0,
            name = model.name?: "",
            status = model.status?: "",
            species = model.species?:"",
            type = model.type ?: "",
            gender= model.gender?: "",
            origin = OriginApiMapper().map(model.origin?: OriginDataApi("","")),
            location = LocationApiMapper().map(model.location?: LocationDataApi ("","")),
            image = model.image?: "",
            episode = list,
            url = model.url ?: "",
            created = model.created ?: "",
        )
    }
}
