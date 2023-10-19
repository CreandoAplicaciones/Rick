package com.rick.and.morty.domain.usercase

import com.rick.and.morty.data.datasources.GetAnimationCharacterDataSource
import com.rick.and.morty.domain.base.Resource
import com.rick.and.morty.domain.model.AnimationCharacter
import com.rick.and.morty.domain.model.CompleteInfo


import javax.inject.Inject

class GetAnimationCharacterUseCase @Inject constructor(
    private val getAnimationCharacterDataSource: GetAnimationCharacterDataSource,
) {

    suspend operator fun invoke(page: Int) : Resource<CompleteInfo> = getAnimationCharacterDataSource.getAnimationCharacterList(page)
}


