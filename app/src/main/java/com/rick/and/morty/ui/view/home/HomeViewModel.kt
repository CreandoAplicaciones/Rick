package com.rick.and.morty.ui.view.home

import androidx.lifecycle.viewModelScope
import com.rick.and.morty.common.Utils
import com.rick.and.morty.domain.base.Resource
import com.rick.and.morty.domain.model.AnimationCharacter
import com.rick.and.morty.domain.usercase.GetAnimationCharacterUseCase
import com.rick.and.morty.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAnimationCharacterUseCase: GetAnimationCharacterUseCase): BaseViewModel() {

    sealed class Event {
        object SetUp: Event()
        object HideKeyboard : Event()
        data class ShowList(val list: List<AnimationCharacter>): Event()
        data class ShowLoading(val isVisible: Boolean): Event()
        data class ShowRetry(val isVisible: Boolean): Event()
        data class ShowToast(val name: String): Event()
    }
    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    private var animationCharacterList =  mutableListOf<AnimationCharacter>()

    //region ViewModel Input
    fun initFlow() {
        doAction(Event.SetUp)
        retrieveAnimationCharacterList()
    }

    fun hideKeyboard() {
        doAction(Event.HideKeyboard)
    }
    fun didClickOnSearch(queryString: String) {
        if (animationCharacterList.isNotEmpty()) {
            doAction(Event.ShowList(Utils.filterLis(animationCharacterList, queryString)))
        }
    }

    private fun retrieveAnimationCharacterList() {
        viewModelScope.launch {
            doAction(Event.ShowLoading(true))
            for (i in 1..42) {
                when (val result = getAnimationCharacterUseCase.invoke(i)) {
                    is Resource.Success -> {
                        animationCharacterList.addAll(result.value.list)
                    }

                    is Resource.Failure -> {
                        doAction(Event.ShowRetry(true))
                        handleError(result)
                    }
                }
            }
            showList()
        }
    }

    private fun showList() {
        if (animationCharacterList.isNotEmpty()){
            doAction(Event.ShowRetry(false))
            doAction(Event.ShowList(animationCharacterList))
        } else {
            doAction(Event.ShowRetry(true))
        }

        doAction(Event.ShowLoading(false))
    }

    fun didSelectedItem(animationCharacter: AnimationCharacter) {
        doAction(Event.ShowToast(animationCharacter.name))

    }


    fun retry() {
        retrieveAnimationCharacterList()
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