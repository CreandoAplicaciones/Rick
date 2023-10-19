package com.rick.and.morty.data.mapper

import com.rick.and.morty.data.base.ListMapper
import com.rick.and.morty.data.base.Mapper
import com.rick.and.morty.data.models.AnimationCharacterDataApi
import com.rick.and.morty.data.models.CompleteInfoDataApi
import com.rick.and.morty.data.models.LocationDataApi
import com.rick.and.morty.domain.model.CompleteInfo
import com.rick.and.morty.domain.model.Location

class CompleteInfoApiMapper: Mapper<CompleteInfoDataApi, CompleteInfo> {
    override fun map(model: CompleteInfoDataApi): CompleteInfo {

        return CompleteInfo(
            ListMapper(AnimationCharacterApiMapper()).map(model.result?: listOf())
        )
    }
}
