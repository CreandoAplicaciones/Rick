package com.rick.and.morty.ui.view.detail

import androidx.lifecycle.viewModelScope
import com.rick.and.morty.common.Utils
import com.rick.and.morty.domain.base.Resource
import com.rick.and.morty.domain.model.AnimationCharacterDetail
import com.rick.and.morty.domain.usercase.GetAnimationCharacterUseCase
import com.rick.and.morty.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getAnimationCharacterUseCase: GetAnimationCharacterUseCase): BaseViewModel() {

    sealed class Event {
        object SetUp: Event()
        data class ShowDetail(val detail: AnimationCharacterDetail): Event()
        data class ShowLoading(val isVisible: Boolean): Event()
        data class ShowRetry(val isVisible: Boolean): Event()
        data class ShowGenderIcon(val resId: Int): Event()
        data class ShowStatusIcon(val resId: Int): Event()
        data class ShowSpeciesIcon(val resId: Int): Event()
        data class ShowOriginIcon(val resId: Int): Event()

    }
    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()


    //region ViewModel Input
    fun initFlow(id: Int) {
        doAction(Event.SetUp)
        retrieveAnimationCharacter(id)
    }


    private fun retrieveAnimationCharacter(id: Int) {
        viewModelScope.launch {
            doAction(Event.ShowLoading(true))
                when (val result = getAnimationCharacterUseCase.invoke(id)) {
                    is Resource.Success -> {
                        result.value
                        doAction(Event.ShowRetry(false))
                        doAction(Event.ShowDetail(result.value))
                        checkIcon(result.value)
                    }

                    is Resource.Failure -> {
                        doAction(Event.ShowRetry(true))
                        handleError(result)
                    }
                }
            doAction(Event.ShowLoading(false))
        }
    }


    private fun checkIcon(detail: AnimationCharacterDetail) {
        doAction(Event.ShowGenderIcon(Utils.getGenderIcon(detail.gender)))
        doAction(Event.ShowStatusIcon(Utils.getStatusIcon(detail.status)))
        doAction(Event.ShowSpeciesIcon(Utils.getSpeciesIcon(detail.species)))
        doAction(Event.ShowOriginIcon(Utils.getOriginIcon(detail.origin.name)))

    }

    fun retry(id: Int) {
        retrieveAnimationCharacter(id)
    }
    //endregion

    //region ViewModel Output
    private fun doAction(event: Event) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }
    //endregion
}