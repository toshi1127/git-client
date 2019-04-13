package com.mzkii.dev.helloflux.data.api

import com.mzkii.dev.helloflux.data.api.response.Repository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRepositoryAPI {
    @GET("/search/repositories")
    fun search(@Query("q") query: String): Single<List<Repository>>
}