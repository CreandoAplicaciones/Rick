package com.rick.and.morty.ui.view.splash

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import com.rick.and.morty.R
import com.rick.and.morty.common.Utils
import com.rick.and.morty.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule
@HiltViewModel
class SplashViewModel @Inject constructor(): BaseViewModel() {

    sealed class Event {
        object SetUp: Event()
        object GoToHome: Event()
        data class ShowAlert(val resIdTitle: Int, val resIdDescription: Int): Event()
    }

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    //region ViewModel Input
    fun initFlow(context: Context) {
        doAction(Event.SetUp)
        checkIfNetworkAvailable(context)
    }

    private fun checkIfNetworkAvailable(context: Context) {
        if (Utils.isNetworkAvailable(context)) {
            Timer().schedule(3000) {
                viewModelScope.launch(Dispatchers.IO) {
                    doAction(Event.GoToHome)
                }
            }
        } else {
            doAction(Event.ShowAlert(R.string.common_no_internet, R.string.common_there_is_no_network_connection))
        }
    }


    fun retry(context: Context) {
        checkIfNetworkAvailable(context)
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