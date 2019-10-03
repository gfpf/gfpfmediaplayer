package br.com.gfpfmediaplayer.service

import br.com.gfpfmediaplayer.service.domain.MediaList
import io.reactivex.Single

class FakeServiceApiImpl : ServiceApi {

    override fun loadAllMediaItem(alt: String, token: String): Single<MediaList> {

        Thread.sleep(2000)

        val values = MEDIA_ITEM_SERVICE_DATA.values
        val list = ArrayList(values)

        val mediaList: MediaList? = MediaList()
        mediaList?.mMediaItems = list

        return Single.just<MediaList>(mediaList)
    }

    companion object {
        private val MEDIA_ITEM_SERVICE_DATA = FakeServiceApiClient.loadAllMediaItem()
    }
}