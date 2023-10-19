package com.rick.and.morty.data.base

data class ErrorResponse (val timestamp: String?,
                          val error: Error?)

data class Error(val code: Int?,
                 val type: String?,
                 val message: String)