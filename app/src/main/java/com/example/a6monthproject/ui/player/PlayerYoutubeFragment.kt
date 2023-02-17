package com.example.a6monthproject.ui.player

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.a6monthproject.base.BaseFragment
import com.example.a6monthproject.data.remote.network.Resource
import com.example.a6monthproject.databinding.FragmentPlayerYoutubeBinding
import com.example.a6monthproject.ui.details.PlayListDetailsFragment
import com.example.a6monthproject.util.youtubeListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerYoutubeFragment : BaseFragment<FragmentPlayerYoutubeBinding, PlayerViewModel>() {
    override val viewModel: PlayerViewModel by viewModel()
    private lateinit var videoId: String

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlayerYoutubeBinding {
        return FragmentPlayerYoutubeBinding.inflate(inflater, container, false)
    }

    override fun initViewModel() {
        videoId = arguments?.getString(PlayListDetailsFragment.VIDEO_ID, "").toString()
        viewModel.getPlayer(videoId).observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    viewModel.loading.value = false
                }
                Resource.Status.ERROR -> {
                    viewModel.loading.value = false
                    Log.e("ololo", "initViewModel: ${it.msg}")
                }
                else -> {
                    viewModel.loading.value = true
                }

            }
        }
    }

    override fun initView() {
        val videoId = arguments?.getString(PlayListDetailsFragment.VIDEO_ID, "").toString()
        val title = arguments?.getString(PlayListDetailsFragment.TITLE, "").toString()
        val description = arguments?.getString(PlayListDetailsFragment.DESCRIPTION, "").toString()

        lifecycle.addObserver(binding.youtubePlayerView)
        binding.tvTitleVideo.text = title
        binding.tvDesVideo.text = description

        binding.youtubePlayerView.addYouTubePlayerListener(youtubeListener { youTubePlayer ->
            youTubePlayer.loadVideo(videoId, 0F)
        })

        binding.btnFull.setOnClickListener {
            if (binding.youtubePlayerView.isFullScreen()) {
                binding.youtubePlayerView.exitFullScreen()

            } else {
                binding.youtubePlayerView.enterFullScreen()
            }
        }
    }
}