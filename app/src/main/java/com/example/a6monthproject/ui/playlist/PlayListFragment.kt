package com.example.a6monthproject.ui.playlist

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
import com.example.a6monthproject.databinding.FragmentPlayListBinding
import com.example.a6monthproject.model.Item
import com.example.a6monthproject.ui.internet.CheckInternet
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListFragment : BaseFragment<FragmentPlayListBinding, PlayListViewModel>() {

    private lateinit var adapter: PlaylistAdapter
    private var newMaxResult = 10

    override val viewModel: PlayListViewModel by viewModel()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlayListBinding {
        return FragmentPlayListBinding.inflate(inflater, container, false)
    }


    override fun initView() {
        adapter = PlaylistAdapter(requireContext(), this::onItemClick)
        binding.rvPlaylist.adapter = adapter


        val checkInternet = CheckInternet(requireContext())
        checkInternet.observe(this) { isConnected ->
            if (!isConnected) {
                viewModel.getPlaylistDB()
            } else {
                viewModel.getPlaylist(newMaxResult)
            }
        }

        binding.rvPlaylist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    newMaxResult += 5
                    viewModel.getPlaylist(newMaxResult)
                }
            }
        })

    }


    private fun onItemClick(item: Item) {
        findNavController().navigate(
            R.id.playListDetailsFragment, bundleOf(
                PLAYLIST_KEY to item.id,
                TITLE to item.snippet?.title,
                DESCRIPTION to item.snippet?.channelTitle,
                SERIES to item.contentDetails?.itemCount.toString()
            )
        )
    }


    override fun initViewModel() {
        viewModel.getPlayListDB.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    viewModel.loading.value = false
                    viewModel.setPlaylistDB(it.data)
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
        viewModel.getPlayList.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    viewModel.loading.value = false
                    viewModel.setPlaylistDB(it.data)
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

    companion object {
        const val PLAYLIST_KEY = "key.id.playlist"
        const val TITLE = "title"
        const val DESCRIPTION = "des"
        const val SERIES = "series"
    }


}