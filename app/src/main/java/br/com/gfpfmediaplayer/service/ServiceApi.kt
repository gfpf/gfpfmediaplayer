package br.com.gfpfmediaplayer.service

import br.com.gfpfmediaplayer.service.domain.MediaList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    // Fetch all mediaItem
    //@GET("/v0/b/desafio-dev-android.appspot.com/o/assets.json?alt=media&token=964a35bb-53d0-45aa-a3dd-ecad72a2f14c")

    //@Headers("token : 964a35bb-53d0-45aa-a3dd-ecad72a2f14c")
    @GET("/v0/b/desafio-dev-android.appspot.com/o/assets.json")
    //fun loadAllMediaItem(): Single<MediaList>
    fun loadAllMediaItem(@Query("alt") alt: String, @Query("access_token") token: String): Single<MediaList>
}