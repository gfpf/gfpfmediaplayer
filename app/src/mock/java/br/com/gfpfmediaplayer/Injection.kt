package br.com.gfpfmediaplayer

import android.content.Context
import br.com.gfpfmediaplayer.repository.MediaListRepository
import br.com.gfpfmediaplayer.repository.Repositories
import br.com.gfpfmediaplayer.service.FakeServiceApiImpl

object Injection {

    fun providesMediaListRepository(): MediaListRepository? {
        return Repositories.getMediaListInMemoryRepository(FakeServiceApiImpl())
    }
}
