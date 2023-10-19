package com.rick.and.morty.ui.base

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rick.and.morty.common.extension.observe
import com.rick.and.morty.ui.base.BaseEvent.ShowMessage
import kotlinx.coroutines.flow.Flow

abstract class BaseActivity: AppCompatActivity() {

    private lateinit var eventsFlow: Flow<BaseEvent>

    override fun attachBaseContext(newBase: Context) {
        val localeUpdatedContext: ContextWrapper = ContextWrapper(newBase)
        super.attachBaseContext(localeUpdatedContext)
    }

    fun init(baseViewModel: BaseViewModel) {
        this.eventsFlow = baseViewModel.baseEventsFlow
        this.eventsFlow.observe(this, ::observeEvent)
    }

    private fun observeEvent(event: BaseEvent) {
        when(event) {
            is ShowMessage -> Toast.makeText(this, event.message, Toast.LENGTH_LONG).show()
        }
    }


}