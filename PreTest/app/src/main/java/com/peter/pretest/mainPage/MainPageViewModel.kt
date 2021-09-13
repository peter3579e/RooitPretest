package com.peter.pretest.mainPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peter.pretest.data.Articles
import com.peter.pretest.data.News
import com.peter.pretest.data.Result
import com.peter.pretest.data.source.PretestRepository
import com.peter.pretest.data.source.local.NewsModel
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class MainPageViewModel(private val pretestRepository: PretestRepository) : ViewModel() {

    val _newList = MutableLiveData<Articles>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val newList: MutableLiveData<Articles>
        get() = _newList

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val apiKey = "1d0e1fa8811a474682b78d9ed7605713"
    private val country = "us"

    init {
        getNews(country, apiKey)
    }

    private fun getNews(country: String, apiKey: String) {
        coroutineScope.launch {
            val result = pretestRepository.getNews(country, apiKey)

            _newList.value = when (result) {
                is Result.Success -> {
                    result.data
                }

                else -> {
                    null
                }
            }
        }
    }

    fun saveTask(title: String, name: String, image: String) {

        try {
            coroutineScope.launch {
                pretestRepository.insertData(title, name, image)
            }
        } catch (e: Exception) {
            throw e
        }

    }


}