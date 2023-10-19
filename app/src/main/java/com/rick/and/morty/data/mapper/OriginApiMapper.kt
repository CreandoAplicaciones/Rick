package com.rick.and.morty.data.mapper

import com.rick.and.morty.data.base.ListMapper
import com.rick.and.morty.data.base.Mapper
import com.rick.and.morty.data.models.AnimationCharacterDataApi
import com.rick.and.morty.data.models.LocationDataApi
import com.rick.and.morty.data.models.OriginDataApi
import com.rick.and.morty.domain.model.AnimationCharacter
import com.rick.and.morty.domain.model.Origin

class OriginApiMapper: Mapper<OriginDataApi, Origin> {
    override fun map(model: OriginDataApi): Origin {

        return Origin(
            name = model.name?: "",
            url = model.url ?: "",
        )
    }
}
