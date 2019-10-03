package br.com.gfpfmediaplayer.service

import androidx.collection.ArrayMap
import br.com.gfpfmediaplayer.service.domain.MediaItem

object FakeServiceApiClient {

    private val FAKE_MEDIA_LIST_SERVICE_DATA: ArrayMap<Int, MediaItem> = ArrayMap(3)

    init {
        addMediaItem(
            1,
            "Fake media item 1",
            "Name 1",
            "https://avatars0.githubusercontent.com/u/2482739?v=4",
            "https://github.com/gfpf"
        )
        addMediaItem(
            2,
            "Fake media item 2",
            "Name 2",
            "https://avatars0.githubusercontent.com/u/18?v=4",
            "https://github.com/nitay"
        )
        addMediaItem(
            3,
            "Fake media item 3",
            "Name 3",
            "https://avatars3.githubusercontent.com/u/26?v=4",
            "https://github.com/kevwil"
        )
    }

    private fun addMediaItem(
        id: Int,
        name: String,
        video: String,
        thumbnail: String,
        audio: String
    ) {
        val item = MediaItem(id, name, video, thumbnail, audio)
        FAKE_MEDIA_LIST_SERVICE_DATA[item.mId] = item
    }


    fun loadAllGHUsers(): ArrayMap<Int, MediaItem> {
        return FAKE_MEDIA_LIST_SERVICE_DATA
    }

}