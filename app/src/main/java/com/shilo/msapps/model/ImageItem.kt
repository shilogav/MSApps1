package com.shilo.msapps.model

import com.google.gson.annotations.SerializedName
class ImageItem {
    @SerializedName("photo")
    lateinit var photo : Array<ImageDetails>
}