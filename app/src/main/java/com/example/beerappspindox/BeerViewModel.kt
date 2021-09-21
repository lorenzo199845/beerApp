package com.example.beerappspindox

import BeerResponse
import BeerResponseItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.BeerRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BeerViewModel : ViewModel() {

    val observableBeerList: MutableLiveData<BeerResponse> = MutableLiveData()
    val observableFilteredBeerList: MutableLiveData<BeerResponse> = MutableLiveData()
    var filteredList : BeerResponse? = BeerResponse()

    private val repository = BeerRepository()

    fun getBeers() {
        viewModelScope.launch {
            val beerListResponse = repository.getBeers()
            observableBeerList.postValue(beerListResponse)
        }
    }

    fun filterBeers(s: String) {
        filteredList?.clear()
        var completeList = observableBeerList.value
        completeList?.forEach {
            if (it.name.startsWith(s,true) || (it.description.startsWith(s,true))){
                filteredList?.add(it)
            }
        }

        observableFilteredBeerList.value = filteredList

    }
}

