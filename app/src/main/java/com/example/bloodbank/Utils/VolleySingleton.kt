package com.example.bloodbank.Utils


import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton private constructor(context: Context) {

    private val mContext: Context = context.applicationContext
    private var mRequestQueue: RequestQueue? = null

    // Singleton instance
    companion object {
        @Volatile
        private var mInstance: VolleySingleton? = null

        fun getInstance(context: Context): VolleySingleton {
            return mInstance ?: synchronized(this) {
                mInstance ?: VolleySingleton(context).also { mInstance = it }
            }
        }
    }

    // Get the RequestQueue
    private fun getRequestQueue(): RequestQueue {
        return mRequestQueue ?: Volley.newRequestQueue(mContext).also { mRequestQueue = it }
    }

    // Add request to the RequestQueue
    fun <T> addToRequestQueue(request: Request<T>) {
        getRequestQueue().add(request)
    }
}
