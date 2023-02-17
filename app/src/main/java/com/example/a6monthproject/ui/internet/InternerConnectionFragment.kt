package com.example.a6monthproject.ui.internet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a6monthproject.base.BaseFragment
import com.example.a6monthproject.databinding.FragmentInternerConnectionBinding
import com.example.a6monthproject.ui.playlist.PlayListViewModel

class InternerConnectionFragment :
    BaseFragment<FragmentInternerConnectionBinding, PlayListViewModel>() {
    override val viewModel: PlayListViewModel by lazy {
        ViewModelProvider(this)[PlayListViewModel::class.java]
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInternerConnectionBinding {
        return FragmentInternerConnectionBinding.inflate(inflater, container, false)
    }

    override fun initViewModel() {
        val checkInternet = CheckInternet(requireContext())
        checkInternet.observe(this) { isConnected ->
            binding.btnTryAgain.setOnClickListener {
                if (isConnected) {
                    findNavController().navigateUp()
                }

            }

        }
    }

    override fun initView() {


    }
}
