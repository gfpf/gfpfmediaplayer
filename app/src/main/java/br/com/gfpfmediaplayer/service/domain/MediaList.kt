package br.com.gfpfmediaplayer.service.domain

import com.google.gson.annotations.SerializedName


class MediaList {

    @SerializedName("objects")
    lateinit var mMediaItems: List<MediaItem?>
}