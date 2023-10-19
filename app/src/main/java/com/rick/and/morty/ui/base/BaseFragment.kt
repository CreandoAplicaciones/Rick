package com.rick.and.morty.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rick.and.morty.R
import com.rick.and.morty.common.extension.observe
import com.rick.and.morty.ui.base.BaseEvent.*
import kotlinx.coroutines.flow.Flow

abstract class BaseFragment: Fragment() {

    private lateinit var eventsFlow: Flow<BaseEvent>


    fun init(baseViewModel: BaseViewModel) {
        this.eventsFlow = baseViewModel.baseEventsFlow
        this.eventsFlow.observe(viewLifecycleOwner, ::observeEvent)
    }

    private fun observeEvent(event: BaseEvent) {
        when(event) {
            is ShowMessage -> context?.let { context ->  Toast.makeText(context, event.message, Toast.LENGTH_LONG).show() }
        }
    }

}