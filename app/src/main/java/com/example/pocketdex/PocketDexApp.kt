package com.example.pocketdex

import androidx.annotation.StringRes

enum class PocketDexScreen(@StringRes val screenTitle: Int, val route: String) {
    HOME(screenTitle = R.string.app_name, route = "home"),
    LOG_IN(screenTitle = R.string.login, route = "log_in"),
    SIGN_UP(screenTitle = R.string.signup, route = "sing_up"),
    PROFILE(screenTitle = R.string.profile, route = "profile")
}
