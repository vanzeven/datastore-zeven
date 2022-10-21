package com.example.mydatastore

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(private val pref: CounterDataStoreManager) : ViewModel() {

    val vCounter: MutableLiveData<Int> = MutableLiveData(0)

    fun incrementCount() {
        viewModelScope.launch {
            pref.incrementCounter()
        }
    }

    fun decrementCount() {
        viewModelScope.launch {
            pref.decrementCounter()
        }
    }

    fun saveDataStore(value: Int) {
        viewModelScope.launch {
            pref.setCounter(value)
        }
    }

    fun getDataStore(): LiveData<Int> {
        return pref.getCounter().asLiveData()
    }
}