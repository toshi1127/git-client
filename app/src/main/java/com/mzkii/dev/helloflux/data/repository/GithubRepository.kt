package com.mzkii.dev.helloflux.data.repository

import com.mzkii.dev.helloflux.data.api.GithubApi
import com.mzkii.dev.helloflux.data.api.SearchRepositoryAPI
import com.mzkii.dev.helloflux.data.api.response.Repository
import io.reactivex.Single

class GithubRepository(
    private val githubApi: GithubApi
//    private val searchRepositoryAPI: SearchRepositoryAPI
) {
    fun getMyRepositoryList(accessToken: String, page: Int): Single<List<Repository>> =
        githubApi.getMyRepositoryList(accessToken, page, perPage = 10)
//    fun getAearchRepositoryList(query: String): Single<List<Repository>> = searchRepositoryAPI.search(query)
}