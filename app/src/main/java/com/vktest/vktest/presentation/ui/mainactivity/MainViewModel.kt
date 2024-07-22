package com.vktest.vktest.presentation.ui.mainactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vktest.domain.usecases.GetValutesUseCase
import com.vktest.vktest.presentation.mapper.toValutesViewData
import com.vktest.vktest.presentation.models.ResultExchange
import com.vktest.vktest.presentation.models.ValuteKey
import com.vktest.vktest.presentation.models.ValuteViewData
import com.vktest.vktest.presentation.models.ValutesViewData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(
    private val valutesUseCase: GetValutesUseCase
) : ViewModel() {
    private val _valutes = MutableLiveData<ValutesViewData?>()
    val valutes: LiveData<ValutesViewData?> = _valutes


    fun getValutes(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = valutesUseCase.execute()
                _valutes.postValue(response.toValutesViewData())
            } catch (e: HttpException) {
                Log.e("MyApp", "HTTP error: ${e.message()}")
                _valutes.postValue(null)
            }
        }
    }

    fun exchange(key : ValuteKey) : ResultExchange {
        val res : ValuteViewData? = valutes.value?.valutes?.get(key.key)
        return ResultExchange(res?.value ?: -1.0)
    }
}