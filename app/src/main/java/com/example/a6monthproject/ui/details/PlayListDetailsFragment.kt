package com.example.a6monthproject.ui.details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.a6monthproject.R
import com.example.a6monthproject.base.BaseFragment
import com.example.a6monthproject.data.remote.network.Resource
import com.example.a6monthproject.databinding.FragmentPlayList2Binding
import com.example.a6monthproject.model.Item
import com.example.a6monthproject.ui.playlist.PlayListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlayListDetailsFragment : BaseFragment<FragmentPlayList2Binding, PlayListDetailsViewModel>() {
    private lateinit var adapter: PlayListDetailsAdapter
    private var newMaxResult = 10
    override val viewModel: PlayListDetailsViewModel by viewModel()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlayList2Binding {
        return FragmentPlayList2Binding.inflate(inflater, container, false)
    }

    override fun initViewModel() {

        val id = arguments?.getString(PlayListFragment.PLAYLIST_KEY)
        val title = arguments?.getString(PlayListFragment.TITLE)
        val des = arguments?.getString(PlayListFragment.DESCRIPTION)
        val series = arguments?.getString(PlayListFragment.SERIES)
        viewModel.getPlayer(id.toString()).observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    viewModel.loading.value = false
                    binding.coorTitle.text = title
                    binding.coorDes.text = des
                    binding.tvSeries.text =
                        requireContext().getString(R.string.series_count, series)
                    it.data?.items?.let { it1 -> adapter.addPlayListItems(it1) }
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
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it
        }


    }

    private fun onItemClick(item: Item) {
        findNavController().navigate(
            R.id.playerYoutubeFragment, bundleOf(
                VIDEO_ID to item.snippet?.resourceId?.videoId,
                TITLE to item.snippet?.title,
                DESCRIPTION to item.snippet?.description,
            )
        )
    }

    override fun initView() {

        adapter = PlayListDetailsAdapter(this::onItemClick)
        binding.rvDetails.adapter = adapter

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.rvDetails.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                newMaxResult += 5

            }
        })
    }

    companion object {
        const val VIDEO_ID = "videoID"
        const val TITLE = "title"
        const val DESCRIPTION = "des"
    }
}