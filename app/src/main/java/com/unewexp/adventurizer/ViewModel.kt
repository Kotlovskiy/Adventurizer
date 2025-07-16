package com.unewexp.adventurizer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unewexp.adventurizer.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class AdventureViewModel: ViewModel() {

    private val apiService = RetrofitClient.instance

    private val _history = MutableStateFlow(emptyList<Activity>())
    val history: StateFlow<List<Activity>> = _history.asStateFlow()
    private var currentPosition: Int = -1
    private var _currentActivity: MutableStateFlow<Activity?> = MutableStateFlow(null)
    val currentActivity: StateFlow<Activity?> = _currentActivity.asStateFlow()

    fun generateNewActivity() = viewModelScope.launch{
        try {
            val response = apiService.getActivity()
            val body = response.body()
            if (response.isSuccessful && body != null){
                _currentActivity.value = body
                _history.update { it + body }
                currentPosition++
            }
        }catch (e: Exception){
            Log.e("AdventureVM", "API error", e)
        }
    }

    //Пока нет хранилища функция ничего не делает
    fun addToFavorites(){}

    fun showPrevActivity(){
        if (currentPosition == -1){
            return
        }
        if (currentPosition > 0){
            currentPosition--
            _currentActivity.value = history.value[currentPosition]
        }
    }

    fun showNextActivity(){
        if (currentPosition == -1){
            return
        }
        if (currentPosition < _history.value.lastIndex){
            currentPosition++
            _currentActivity.value = history.value[currentPosition]
        }
    }
}