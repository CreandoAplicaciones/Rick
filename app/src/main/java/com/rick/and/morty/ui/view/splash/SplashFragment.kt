package com.rick.and.morty.ui.view.splash

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rick.and.morty.R
import com.rick.and.morty.common.extension.observe
import com.rick.and.morty.databinding.FragmentSplashBinding
import com.rick.and.morty.ui.base.BaseFragment
import com.rick.and.morty.ui.view.splash.SplashViewModel.Event.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment: BaseFragment() {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.init(viewModel)


    }

    override fun onResume() {
        super.onResume()
        activity?.let { activity-> viewModel.initFlow(activity) }
        viewModel.eventsFlow.observe(viewLifecycleOwner, ::updateUi)
    }

    private fun updateUi(model: SplashViewModel.Event) {
        when (model) {
            is SetUp -> {
                val videoPath = "android.resource://" + "com.rick.and.morty" + "/" + R.raw.background_video
                val videoUri = Uri.parse(videoPath)

                // Establecer la URI del video en el VideoView y comenzar la reproducción
                binding.videoView.setVideoURI(videoUri)
                binding.videoView.start()

                // Agregar el OnCompletionListener para repetir el video una vez que termine
                binding.videoView.setOnCompletionListener {
                    binding.videoView.start()
                }
            }
            is GoToHome -> findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            is ShowAlert -> alert(getString(model.resIdTitle), getString(model.resIdDescription), viewModel::retry)
        }
    }
    private fun alert(title: String, description: String, action: (Context) -> Unit) {
        activity?.let { activity->
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(title)
                .setMessage(description)
                .setPositiveButton(R.string.common_retry) { _, _ ->
                    action.invoke(activity)
                }
                .show()
        }

    }
}
