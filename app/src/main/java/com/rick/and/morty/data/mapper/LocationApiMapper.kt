package com.rick.and.morty.data.mapper

import com.rick.and.morty.data.base.Mapper
import com.rick.and.morty.data.models.LocationDataApi
import com.rick.and.morty.domain.model.Location

class LocationApiMapper: Mapper<LocationDataApi, Location> {
    override fun map(model: LocationDataApi): Location {

        return Location(
            name = model.name?: "",
            url = model.url ?: "",
        )
    }
}
