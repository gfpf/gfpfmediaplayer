package br.com.gfpfmediaplayer.repository

import androidx.annotation.VisibleForTesting
import br.com.gfpfmediaplayer.service.RetrofitServiceApiClient
import br.com.gfpfmediaplayer.service.domain.MediaItem
import br.com.gfpfmediaplayer.service.domain.MediaList
import br.com.gfpfmediaplayer.service.ServiceApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MediaListInMemoryRepository(serviceApi: ServiceApi) : MediaListRepository {

    private val mServiceApi: ServiceApi = serviceApi

    /**
     * This method has reduced visibility for testing and is only visible to tests in the same package.
     */
    @VisibleForTesting
    private var mCachedResults: List<MediaList>? = null

    override fun loadAllMediaItem(callback: DisposableSingleObserver<MediaList>?): Single<MediaList> {
        return mServiceApi.loadAllMediaItem(
            RetrofitServiceApiClient.QUERY_ALT_KEY,
            RetrofitServiceApiClient.QUERY_TOKEN_KEY
        )
            //return mServiceApi.loadAllMediaItem()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun refreshData() {
        mCachedResults = null
    }
}