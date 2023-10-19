package com.rick.and.morty.data.mapper

import com.rick.and.morty.data.base.Mapper
import com.rick.and.morty.data.models.AnimationCharacterDataApi
import com.rick.and.morty.domain.model.AnimationCharacter

class AnimationCharacterApiMapper: Mapper<AnimationCharacterDataApi, AnimationCharacter> {
    override fun map(model: AnimationCharacterDataApi): AnimationCharacter {
        return AnimationCharacter(
            id = model.id?: 0,
            name = model.name?: "",
            status = model.status?: "",
            species = model.species?:"",
            gender= model.gender?: "",
            image = model.image?: "",
        )
    }
}
