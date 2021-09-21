package com.example.network

import BeerResponse
import java.lang.Exception

class BeerRepository {

    private val request = RetrofitManager.retrofit.create(ApiInterface::class.java)

    suspend fun getBeers(): BeerResponse? {
        return try {
                request.getAllBeers()

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }



}