package com.example.a6monthproject.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.a6monthproject.R

abstract class BaseFragment<VB: ViewBinding, VM: BaseViewModel> : Fragment() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateViewBinding(inflater, container)
        return binding.root
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?):VB
    abstract fun initViewModel()
    abstract fun initView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }




}