package com.mzkii.dev.helloflux.data.repository

import com.mzkii.dev.helloflux.data.api.SearchRepositoryAPI
import com.mzkii.dev.helloflux.data.api.response.Repository
import io.reactivex.Single

class SearchRepository(
    private val searchRepositoryAPI: SearchRepositoryAPI
) {
    fun getAearchRepositoryList(query: String): Single<List<Repository>> = searchRepositoryAPI.search(query)
}