package com.rick.and.morty.data.base

interface Mapper<M, P> {
    fun map(model: M): P

}