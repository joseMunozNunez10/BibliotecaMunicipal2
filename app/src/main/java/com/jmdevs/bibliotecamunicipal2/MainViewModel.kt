package com.jmdevs.bibliotecamunicipal2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val repository = BookRepository()

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var searchJob: Job? = null

    fun searchBooks(query: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            _isLoading.value = true

            val searchResult = async(Dispatchers.IO) {
                repository.search(query)
            }

            val result = searchResult.await()

            withContext(Dispatchers.Main) {
                _books.value = result
                _isLoading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}
