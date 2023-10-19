package com.rick.and.morty.data.mapper

import com.rick.and.morty.data.base.ListMapper
import com.rick.and.morty.data.base.Mapper
import com.rick.and.morty.data.models.CompleteInfoDataApi
import com.rick.and.morty.domain.model.CompleteInfo

class CompleteInfoApiMapper: Mapper<CompleteInfoDataApi, CompleteInfo> {
    override fun map(model: CompleteInfoDataApi): CompleteInfo {

        return CompleteInfo(
            ListMapper(AnimationCharacterApiMapper()).map(model.result?: listOf())
        )
    }
}
