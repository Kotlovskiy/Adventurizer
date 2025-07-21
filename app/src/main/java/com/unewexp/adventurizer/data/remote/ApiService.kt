package com.unewexp.adventurizer.data.remote

import com.unewexp.adventurizer.Activity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("random-item")
    suspend fun getActivity(): Response<Activity>
}