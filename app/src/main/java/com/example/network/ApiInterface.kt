package com.example.network

import BeerResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET(RetrofitManager.BEER_ENDPOINT)
    suspend fun getAllBeers() : BeerResponse




}