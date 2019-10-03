package br.com.gfpfmediaplayer.repository

import br.com.gfpfmediaplayer.service.domain.MediaList
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

interface MediaListRepository {

    fun loadAllMediaItem(callback: DisposableSingleObserver<MediaList>?): Single<MediaList>

    fun refreshData()
}