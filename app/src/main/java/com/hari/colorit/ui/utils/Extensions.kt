package com.hari.colorit.ui.utils

import android.util.Patterns

object Extensions {

    fun String.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}