package br.com.gfpfmediaplayer.ui.media

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gfpfmediaplayer.Injection
import br.com.gfpfmediaplayer.repository.MediaListRepository
import br.com.gfpfmediaplayer.service.domain.MediaItem
import br.com.gfpfmediaplayer.service.domain.MediaList
import br.com.gfpfmediaplayer.service.domain.MediaListContract
import io.reactivex.Single

class MediaListViewModel : ViewModel(),
    MediaListContract.UserActionsListener {

    private val mMediaListRepository: MediaListRepository? = Injection.providesMediaListRepository()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    override fun loadAllMediaItem(): Single<MediaList>? {
        return mMediaListRepository?.loadAllMediaItem(null)
    }
}