package br.com.gfpfmediaplayer

import br.com.gfpfmediaplayer.repository.MediaListRepository
import br.com.gfpfmediaplayer.repository.Repositories
import br.com.gfpfmediaplayer.service.RetrofitServiceApiClient
import br.com.gfpfmediaplayer.service.ServiceApi

object Injection {

    fun providesMediaListRepository(): MediaListRepository? {
        val serviceApi = RetrofitServiceApiClient.getClient()?.create(ServiceApi::class.java)
        return serviceApi?.let { Repositories.getMediaListInMemoryRepository(it) }
    }
}