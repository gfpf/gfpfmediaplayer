package br.com.gfpfmediaplayer.service

import br.com.gfpfmediaplayer.service.domain.MediaList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    // Fetch all mediaItem
    @GET("/v0/b/desafio-dev-android.appspot.com/o/assets.json")
    fun loadAllMediaItem(@Query("alt") alt: String, @Query("access_token") token: String): Single<MediaList>
}