package com.vktest.vktest.presentation.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vktest.checknet.CheckNetwork
import com.vktest.vktest.R
import com.vktest.vktest.app.MyApp
import com.vktest.vktest.databinding.FragmentCheckBinding
import javax.inject.Inject

class CheckFragment : Fragment() {
    private lateinit var binding: FragmentCheckBinding
    @Inject
    lateinit var checkNetwork: CheckNetwork

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

        checkNetwork()

    }


    private fun checkNetwork() {
        val attempt = 0
        looperCheckNetwork(attempt)
    }

    private fun looperCheckNetwork(attempt: Int) {
        if (checkNetwork.isNetworkAvailable()) {
            val nextFragment = MainFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, nextFragment)
                .addToBackStack(null)
                .commit()
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

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showNoConnectionText() {
        binding.progressBar.visibility = View.GONE
        binding.textNoConnection.visibility = View.VISIBLE
    }
}