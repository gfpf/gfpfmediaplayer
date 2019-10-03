package br.com.gfpfmediaplayer.service

import br.com.gfpfmediaplayer.service.domain.MediaItem
import io.reactivex.Single

class FakeServiceApiImpl : ServiceApi {

    companion object {
        private val MEDIA_ITEM_SERVICE_DATA = FakeServiceApiClient.loadAllGHUsers()
    }

    override fun loadAllMediaItem(): Single<List<MediaItem>> {
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        val values = MEDIA_ITEM_SERVICE_DATA.values
        val list = ArrayList(values)
        return Single.just<List<MediaItem>>(list)
    }
}