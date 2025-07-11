package com.unewexp.adventurizer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class AdventureViewModel: ViewModel() {
    private val _history = MutableStateFlow(emptyList<Activity>())
    val history: StateFlow<List<Activity>> = _history.asStateFlow()
    private var currentPosition: Int = -1
    private var _currentActivity: MutableStateFlow<Activity?> = MutableStateFlow(null)
    val currentActivity get() = _currentActivity.value

    fun generateNewActivity(){
        //пока нет сетевых запросов создаём заглушку со случайным ID и заголовком
        _currentActivity.value = Activity(
            id = UUID.randomUUID().toString(),
            title = UUID.randomUUID().toString().substring(0, 8),
            category = "cooking",
            difficulty = "Легко",
            price = 0f
        )

        _history.update { it + _currentActivity.value!! }
        currentPosition++
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