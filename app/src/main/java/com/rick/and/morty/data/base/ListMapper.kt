package com.rick.and.morty.data.base

import androidx.annotation.NonNull

class ListMapper<M, P>(private val mapper: Mapper<M, P>) : Mapper<List<M>, List<P>> {

    override fun map(@NonNull models: List<M>): List<P> {
        val result = ArrayList<P>()

        if (models.isNotEmpty()) {
            for (model in models) {
                result.add(mapper.map(model))
            }
        }
        return result
    }

}