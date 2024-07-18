package com.example.network

import androidx.annotation.StringRes

interface ResourcesResolver {

    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String
}