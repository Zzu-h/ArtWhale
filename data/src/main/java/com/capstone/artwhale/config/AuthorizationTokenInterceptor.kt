package com.capstone.artwhale.config

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import com.capstone.artwhale.common.SharedPreferencesManager
import com.capstone.artwhale.common.SharedPreferencesManager.Companion.X_ACCESS_TOKEN
import javax.inject.Inject

class AuthorizationTokenInterceptor @Inject constructor(
    private val sharedPreferencesManager: com.capstone.artwhale.common.SharedPreferencesManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        sharedPreferencesManager.getJwt()?.let { builder.addHeader(X_ACCESS_TOKEN, it) }

        return chain.proceed(builder.build())
    }
}