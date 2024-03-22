package com.tui.data.source.remote.core

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkResponseCall<S : Any>(private val delegate: Call<S>) : Call<NetworkResponse<S>> {
    override fun clone(): Call<NetworkResponse<S>> {
        return NetworkResponseCall(delegate.clone())
    }

    override fun execute(): Response<NetworkResponse<S>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }

    override fun enqueue(callback: Callback<NetworkResponse<S>>) {
        delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Success(body))
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Error)
                        )
                    }
                } else {
                    callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(NetworkResponse.Error)
                    )
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                callback.onResponse(this@NetworkResponseCall, Response.success(NetworkResponse.Error))
            }
        })
    }
}
