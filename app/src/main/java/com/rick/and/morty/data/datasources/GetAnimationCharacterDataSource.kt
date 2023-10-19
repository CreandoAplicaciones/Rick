package com.rick.and.morty.data.datasources

import com.rick.and.morty.data.api.RickAndMortyApiClient
import com.rick.and.morty.data.base.BaseDataSource
import com.rick.and.morty.data.mapper.*
import com.rick.and.morty.domain.base.Resource
import com.rick.and.morty.domain.model.*
import javax.inject.Inject

class GetAnimationCharacterDataSource @Inject constructor(private val api: RickAndMortyApiClient): BaseDataSource() {

    suspend fun getAnimationCharacterList(id: Int): Resource<AnimationCharacterDetail> = safeApiCall(AnimationCharacterDetailApiMapper()) {
        api.getAnimationCharacter(id)
    }

}
