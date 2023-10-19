package com.rick.and.morty.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rick.and.morty.R
import com.rick.and.morty.domain.base.FailureError
import com.rick.and.morty.domain.base.Resource
import com.rick.and.morty.ui.base.BaseEvent.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    private val baseEventChannel = Channel<BaseEvent>(Channel.BUFFERED)
    val baseEventsFlow = baseEventChannel.receiveAsFlow()

    /**
     * handleError send a BaseEvent from BaseViewModel to BaseActivity or BaseFragment
     */
    fun handleError(failure: Resource.Failure) {
        when(failure.type) {
            FailureError.Mapping -> showMessage(R.string.common_error_mapping)
            FailureError.Network -> showMessage(R.string.common_error_network)
            else -> showMessage(R.string.common_error_generic)
        }
    }

    fun showMessage(resId: Int) = doEvent(ShowMessage(resId))

    private fun doEvent(event: BaseEvent) {
        viewModelScope.launch {
            baseEventChannel.send(event)
        }
    }
}