package com.vktest.vktest.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vktest.vktest.app.MyApp
import com.vktest.vktest.databinding.FragmentMainBinding
import com.vktest.vktest.presentation.ui.mainactivity.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    @Inject lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MyApp).myAppComponent.inject(this)
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("MyTag", "i am a main fragment")
        Log.d("MyTag", viewModel.valutes.value.toString())

    }

}