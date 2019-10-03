package br.com.gfpfmediaplayer.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object RetrofitServiceApiClient {

    private const val BASE_URL = "https://firebasestorage.googleapis.com"
    const val QUERY_ALT_KEY = "media"
    const val QUERY_TOKEN_KEY = "964a35bb-53d0-45aa-a3dd-ecad72a2f14c"

    private var retrofit: Retrofit? = null
    private const val REQUEST_TIMEOUT = 10
    private var okHttpClient: OkHttpClient? = null

    fun getClient(): Retrofit? {

        if (okHttpClient == null)
            initOkHttp()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    private fun initOkHttp() {

        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {

                val originalRequest = chain.request()
                val authenticationRequest = originalRequest.newBuilder()
                    .header("TestHeader", "fail")
                    .build()

                val origResponse = chain.proceed(authenticationRequest)

                // server should give us a 403, since the header contains 'fail'
                if (origResponse.code == 403) {
                    val refreshToken = "abcd" // you got this from Auth0 when logging in

                    // start a new synchronous network call to Auth0
                    //val newIdToken = fetchNewIdTokenFromAuth0(refreshToken)
                    val newIdToken = QUERY_TOKEN_KEY

                    // make a new request with the new id token
                    val newAuthenticationRequest = originalRequest.newBuilder()
                        .header("TestHeader", "succeed")
                        .build()

                    // try again

                    // hopefully we now have a status of 200
                    return chain.proceed(newAuthenticationRequest)
                } else {
                    return origResponse
                }




                /*val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    //.addHeader("Authorization", "964a35bb-53d0-45aa-a3dd-ecad72a2f14c")*/

                //=======================================================
                // Adding Authorization token (API Key)
                // Requests will be denied without API key
                /*if (!TextUtils.isEmpty(PrefUtils.getApiKey(context))) {
                    requestBuilder.addHeader("Authorization", PrefUtils.getApiKey(context));
                }*/
                //=======================================================

                /*val request = requestBuilder
                    .build()

                return chain.proceed(request)*/
            }
        })

        okHttpClient = httpClient.build()
    }
}