package com.rick.and.morty.domain.base

sealed class FailureError {

    /** Base Failure Errors */
    object Mapping: FailureError()
    object Network: FailureError()
    object Generic: FailureError()


    /** Other Failure Errors */

}