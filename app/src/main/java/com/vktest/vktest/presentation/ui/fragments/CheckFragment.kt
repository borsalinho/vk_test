package com.vktest.vktest.presentation.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.vktest.checknet.CheckNetwork
import com.vktest.vktest.R
import com.vktest.vktest.app.MyApp
import com.vktest.vktest.databinding.FragmentCheckBinding
import com.vktest.vktest.presentation.ui.mainactivity.MainViewModel
import javax.inject.Inject

class CheckFragment : Fragment() {
    private lateinit var binding: FragmentCheckBinding
    @Inject
    lateinit var checkNetwork: CheckNetwork

    @Inject
    lateinit var viewModel: MainViewModel

    private val attempts = 10
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as MyApp).myAppComponent.inject(this)
        binding = FragmentCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNetworkAndApi()

    }

    private fun checkNetworkAndApi() {
        if (checkNetwork.isNetworkAvailable()) {
            checkApi()
        } else {
            val attempt = 0
            looperCheckNetwork(attempt)
        }
    }

    private fun looperCheckNetwork(attempt: Int) {
        if (checkNetwork.isNetworkAvailable()) {
            checkApi()
        } else {
            if (attempt < attempts) {
                showToast("No internet connection")
                Handler(Looper.getMainLooper()).postDelayed({
                    looperCheckNetwork(attempt + 1)
                }, 2000)
            } else {
                showNoConnectionText()
            }
        }
    }

    private fun checkApi() {
        viewModel.getValutes()
        viewModel.valutes.observe(viewLifecycleOwner, Observer { valutes ->
            if (valutes != null) {
                openMainFragment()
            } else {
                showNoApiText()
            }
        })
    }

    private fun openMainFragment() {
        val nextFragment = MainFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, nextFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showNoConnectionText() {
        binding.progressBar.visibility = View.GONE
        binding.textNoConnection.visibility = View.VISIBLE
    }
    private fun showNoApiText() {
        binding.progressBar.visibility = View.GONE
        binding.textNoApi.visibility = View.VISIBLE
    }
}