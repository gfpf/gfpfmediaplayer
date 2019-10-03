package br.com.gfpfmediaplayer.service.domain

import io.reactivex.Single

interface MediaListContract {

    interface View {
        fun setProgressIndicator(active: Boolean)

        fun showToastMessage(message: String)

        fun showMediaListUI(mediaList: MediaList?, isAppend: Boolean)

        fun showMediaItemDetailUI(requestedMedia: MediaItem)
    }

    interface UserActionsListener {
        fun loadAllMediaItem(): Single<MediaList>?
    }
}