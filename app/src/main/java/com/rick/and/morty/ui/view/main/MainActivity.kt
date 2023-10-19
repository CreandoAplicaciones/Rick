package com.rick.and.morty.ui.view.main

import android.os.Bundle
import android.text.format.DateFormat
import androidx.activity.viewModels
import com.rick.and.morty.common.extension.observe
import com.rick.and.morty.databinding.ActivityMainBinding
import com.rick.and.morty.ui.base.BaseActivity
import com.rick.and.morty.ui.view.main.MainViewModel.Event.Menu
import com.rick.and.morty.ui.view.main.MainViewModel.Event.SetUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.init(viewModel)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.initFlow()
        viewModel.eventsFlow.observe(this, ::updateUi)
    }

    private fun updateUi(model: MainViewModel.Event) {
        when (model) {
            is SetUp -> {}
            is Menu -> {
//                val navController = findNavController(R.id.nav_host_fragment_activity_main)
//                binding.bottomNavigationView.setOnItemSelectedListener { item ->
//                    when (item.itemId) {
//                        R.id.fingerprintFragment -> {
//                            Prefs.saveNumberList(this.getSharedPreferences(SHARED_NAME, 0), 2)
//                            navController.navigate(R.id.action_fingerprint)
//                            true
//                        }
//
//                        R.id.testFragment-> {
//                            Prefs.saveNumberList(this.getSharedPreferences(SHARED_NAME, 0), 1)
//                            navController.navigate(R.id.action_test)
//                            true
//                        }
//
//                        R.id.classicFragment -> {
//                            Prefs.saveNumberList(this.getSharedPreferences(SHARED_NAME, 0), 0)
//                            navController.navigate(R.id.action_classic)
//                            true
//                        }
//
//                        else -> false
//                    }
//                }
            }
        }
    }




}

