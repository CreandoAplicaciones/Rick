package com.rick.and.morty.ui.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.rick.and.morty.R
import com.rick.and.morty.common.Utils
import com.rick.and.morty.common.extension.observe
import com.rick.and.morty.databinding.FragmentDetailBinding
import com.rick.and.morty.ui.base.BaseFragment
import com.rick.and.morty.ui.view.detail.DetailViewModel.Event.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: BaseFragment() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding

    private var id = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.init(viewModel)
        id = arguments?.getInt("argumentoInt") ?: 0
        viewModel.initFlow(id)
        viewModel.eventsFlow.observe(viewLifecycleOwner, ::updateUi)
    }

    private fun updateUi(model: DetailViewModel.Event) {
        when (model) {
            is SetUp -> binding.btnRetry.setOnClickListener { viewModel.retry(id) }
            is ShowLoading -> binding.progressBar.isVisible = model.isVisible
            is ShowRetry -> binding.constraintLayoutRetry.isVisible = model.isVisible
            is ShowDetail -> {
                binding.txtTitle.text = model.detail.name
                binding.txtStatus.text = getString(R.string.detail_status, model.detail.status)
                binding.txtSpecies.text = getString(R.string.detail_species, model.detail.species)
                binding.txtGender.text = getString(R.string.detail_gender, model.detail.gender)
                binding.txtOrigin.text = getString(R.string.detail_origen, model.detail.origin.name)
                binding.txtLocation.text = getString(R.string.detail_location, model.detail.name, model.detail.location.name)
                binding.txtEpisode.text = getString(R.string.detail_episode, model.detail.name, model.detail.episode.size.toString())
                activity?.let { activity ->
                    Glide.with(activity)
                        .load(model.detail.image)
                        .centerCrop()
                        .into(binding.imageView)
                }
            }
            is ShowGenderIcon -> binding.imgGender.setImageResource(model.resId)
            is ShowOriginIcon -> binding.imgOrigin.setImageResource(model.resId)
            is ShowSpeciesIcon -> binding.imgSpecies.setImageResource(model.resId)
            is ShowStatusIcon -> binding.imgStatus.setImageResource(model.resId)
        }
    }

}
