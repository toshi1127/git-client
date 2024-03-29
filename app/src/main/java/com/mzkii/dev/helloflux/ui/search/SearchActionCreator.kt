package com.mzkii.dev.helloflux.ui.home

import com.mzkii.dev.flux.ActionCreator
import com.mzkii.dev.flux.Dispatcher
import com.mzkii.dev.githubgistclient.data.preferences.AppSetting
import com.mzkii.dev.helloflux.data.repository.GithubRepository
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchActionCreator(
    private val appSetting: AppSetting,
    private val repository: GithubRepository,
    dispatcher: Dispatcher
) : ActionCreator<HomeAction>(dispatcher) {

    fun getMyRepositoryList(page: Int) =
        repository
            .getMyRepositoryList(appSetting.accessToken, page)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(HomeAction.ShowLoading(true)) }
            .doFinally { dispatch(HomeAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = { dispatch(HomeAction.LoadRepositoryList(it)) },
                onError = { Timber.e(it) }
            )
}