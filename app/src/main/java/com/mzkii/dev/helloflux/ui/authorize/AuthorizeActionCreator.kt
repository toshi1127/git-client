package com.mzkii.dev.helloflux.ui.authorize

import android.content.Intent
import com.mzkii.dev.flux.ActionCreator
import com.mzkii.dev.flux.Dispatcher
import com.mzkii.dev.helloflux.BuildConfig
import com.mzkii.dev.helloflux.data.repository.AuthorizeRepository
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AuthorizeActionCreator( // Authorizeアクションクラスを作成するActionCreator
    private val repository: AuthorizeRepository,
    dispatcher: Dispatcher
) : ActionCreator<AuthorizeAction>(dispatcher) { // dispatcherがコンストラクタの引数
    fun authorize(intent: Intent) {
        val uri = intent.data ?: return
        if (!uri.toString().startsWith(BuildConfig.REDIRECT_URI)) return
        repository // APIを叩くため、別スレッドで実行
            .getAccessToken(uri.getQueryParameter("code")!!) // callbackして取得したIntent中に含まれるcodeをrepository.getAccessTokenに渡し、アクセストークンを取得
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { dispatch(AuthorizeAction.Authorize(it)) }, //AuthorizeActionの発火
                onError = { Timber.e(it) }
            )
    }
}