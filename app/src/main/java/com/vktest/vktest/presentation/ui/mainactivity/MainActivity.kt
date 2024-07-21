package com.vktest.vktest.presentation.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vktest.checknet.CheckNetwork
import com.vktest.vktest.R
import com.vktest.vktest.app.MyApp
import com.vktest.vktest.databinding.ActivityMainBinding
import com.vktest.vktest.presentation.ui.fragments.CheckFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApp).myAppComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CheckFragment())
                .commit()
        }
    }
}