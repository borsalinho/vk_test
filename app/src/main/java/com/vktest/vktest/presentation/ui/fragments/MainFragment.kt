package com.vktest.vktest.presentation.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.vktest.vktest.R
import com.vktest.vktest.app.MyApp
import com.vktest.vktest.databinding.FragmentMainBinding
import com.vktest.vktest.presentation.models.ValuteKey
import com.vktest.vktest.presentation.ui.mainactivity.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var spinner: Spinner
    private lateinit var editRUB: TextView
    private lateinit var textValute: TextView
    private lateinit var textResult: TextView

    private lateinit var btnExchange: Button
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
        initValues()

        createSpinner()
        setValutes()
        buttonScript()

    }

    private fun initValues(){
        spinner = binding.spinner
        editRUB = binding.editRUB
        btnExchange = binding.btnExchange
        textValute = binding.textValute
        textResult = binding.textResult
    }

    private fun buttonScript(){
        editRUB.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btnExchange.isEnabled = s != null && s.isNotEmpty()
            }
        })
        btnExchange.setOnClickListener {
            val value = viewModel.exchange(ValuteKey(textValute.text.toString()))
            val res: Double
            if (value.result != -1.0) {
                res = value.result * editRUB.text.toString().toDouble()
                textResult.text = String.format("%.2f", res)
            }
        }
    }

    private fun createSpinner(){
        valutesAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item)
        valutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = valutesAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedValute = parent.getItemAtPosition(position) as String
                textValute.text = selectedValute.substringBefore(" ")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //
            }
        }
    }

    private fun setValutes(){
        viewModel.valutes.observe(viewLifecycleOwner, Observer { valutes ->
            valutes?.let { it ->
                valutesAdapter.clear()
                valutesAdapter.addAll(it.valutes.map {
                    it.key + " (${it.value.name}) "
                } )
            }
        })
    }

}