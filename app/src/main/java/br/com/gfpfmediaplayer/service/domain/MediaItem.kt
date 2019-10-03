package br.com.gfpfmediaplayer.service.domain

import com.google.gson.annotations.SerializedName

class MediaItem(id: Int?, name: String?, video: String?, thumbnail: String?, audio: String?) {

    @SerializedName("id")
    var mId = id

    @SerializedName("name")
    var mName = name

    @SerializedName("bg")
    var mVideo = video

    @SerializedName("im")
    var mThumbnail = thumbnail

    @SerializedName("sg")
    var mAudio = audio
}