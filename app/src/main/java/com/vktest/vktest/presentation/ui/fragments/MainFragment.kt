package com.vktest.vktest.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.vktest.vktest.R
import com.vktest.vktest.app.MyApp
import com.vktest.vktest.databinding.FragmentMainBinding
import com.vktest.vktest.presentation.ui.mainactivity.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var spinner: Spinner
    private lateinit var valutesAdapter: ArrayAdapter<String>

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

        createSpinner()
        setValutes()


    }

    private fun createSpinner(){
        spinner = binding.spinner
        valutesAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item)
        valutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = valutesAdapter
    }

    private fun setValutes(){
        viewModel.valutes.observe(viewLifecycleOwner, Observer { valutes ->
            valutes?.let {
                valutesAdapter.clear()
                valutesAdapter.addAll(it.valutes.map { it.key })
            }
        })
    }

}