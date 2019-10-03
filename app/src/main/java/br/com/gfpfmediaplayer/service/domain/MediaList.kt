package br.com.gfpfmediaplayer.service.domain

import com.google.gson.annotations.SerializedName


class MediaList {

    @SerializedName("objects")
    private lateinit var mMediaItems: List<MediaItem?>
}