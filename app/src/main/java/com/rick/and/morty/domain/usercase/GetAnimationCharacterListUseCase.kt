package com.rick.and.morty.domain.usercase

import com.rick.and.morty.data.datasources.GetAnimationCharacterListDataSource
import com.rick.and.morty.domain.base.Resource
import com.rick.and.morty.domain.model.CompleteInfo


import javax.inject.Inject

class GetAnimationCharacterListUseCase @Inject constructor(
    private val getAnimationCharacterListDataSource: GetAnimationCharacterListDataSource,
) {

    suspend operator fun invoke(page: Int) : Resource<CompleteInfo> = getAnimationCharacterListDataSource.getAnimationCharacterList(page)
}


