package com.rick.and.morty.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rick.and.morty.R
import com.rick.and.morty.common.Utils
import com.rick.and.morty.common.extension.observe
import com.rick.and.morty.databinding.FragmentHomeBinding
import com.rick.and.morty.ui.base.BaseFragment
import com.rick.and.morty.ui.view.home.HomeViewModel.Event.*

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.init(viewModel)
        viewModel.initFlow()
        viewModel.eventsFlow.observe(viewLifecycleOwner, ::updateUi)
    }

    private fun updateUi(model: HomeViewModel.Event) {
        when (model) {
            is SetUp -> {
                binding.constraint.setOnClickListener { viewModel.hideKeyboard() }
                binding.btnRetry.setOnClickListener { viewModel.retry() }
                binding.cardViewRecycleView.setOnClickListener { viewModel.hideKeyboard() }
                binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(qString: String): Boolean {
                        viewModel.didClickOnSearch(qString)
                        return true
                    }
                    override fun onQueryTextSubmit(qString: String): Boolean = false
                })
            }
            is ShowList -> binding.rVList.adapter = HomeAdapter( model.list, viewModel::didSelectedItem)
            is HideKeyboard -> Utils.hideKeyboard(binding.constraint)
            is ShowLoading -> binding.progressBar.isVisible = model.isVisible
            is ShowRetry -> binding.constraintLayoutRetry.isVisible = model.isVisible
            is ShowToast -> activity?.let { actovity-> Utils.toast(actovity,model.name ) }
            is GoToDetail -> findNavController().navigate(R.id.action_homeFragment_to_detailFragment, Bundle().apply {
                putInt("argumentoInt", model.id)
            })
        }
    }

}
