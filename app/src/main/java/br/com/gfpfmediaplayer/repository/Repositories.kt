package br.com.gfpfmediaplayer.repository

import br.com.gfpfmediaplayer.service.ServiceApi

object Repositories {

    private var repository: MediaListRepository? = null

    @Synchronized
    fun getMediaListInMemoryRepository(serviceApi: ServiceApi): MediaListRepository? {

        if (repository == null) {
            repository = MediaListInMemoryRepository(serviceApi)
        }
        return repository
    }

}
