package com.shilo.msapps.viewmoldel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shilo.msapps.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var repository: Repository = Repository()
    val itemMutableLiveData = repository.itemMutableLiveData

    init {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getArray()
        }
    }
}