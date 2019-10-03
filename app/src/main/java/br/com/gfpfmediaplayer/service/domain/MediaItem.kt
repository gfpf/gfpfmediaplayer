package br.com.gfpfmediaplayer.service.domain

import com.google.gson.annotations.SerializedName

class MediaItem(id: Int?, name: String?, video: String?, thumbnail: String?, audio: String?) {

    @SerializedName("id")
    var mId = id

    @SerializedName("name")
    private var mName = name

    @SerializedName("bg")
    private var mVideo = video

    @SerializedName("im")
    private var mThumbnail = thumbnail

    @SerializedName("sg")
    private var mAudio = audio
}