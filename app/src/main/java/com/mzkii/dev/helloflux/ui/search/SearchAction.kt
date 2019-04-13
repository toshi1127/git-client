package com.mzkii.dev.helloflux.ui.home

import com.mzkii.dev.helloflux.data.api.response.Repository

sealed class SearchAction {

    class ShowLoading(val isLoading: Boolean) : SearchAction()
    class LoadRepositoryList(val repositoryList: List<Repository>) : SearchAction()
    class LoadSearchRepositoryList(val repositoryList: List<Repository>) : SearchAction()
}