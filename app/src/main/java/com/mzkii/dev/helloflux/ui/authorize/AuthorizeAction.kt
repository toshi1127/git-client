package com.mzkii.dev.helloflux.ui.authorize

import com.mzkii.dev.helloflux.data.api.response.AccessToken

sealed class AuthorizeAction { //同じファイル内で宣言されたクラスのみ継承可能にする
    class Authorize(val accessToken: AccessToken) : AuthorizeAction()
}