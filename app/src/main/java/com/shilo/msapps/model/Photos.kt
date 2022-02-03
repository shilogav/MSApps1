package com.shilo.msapps.model

import com.google.gson.annotations.SerializedName

class Photos {
    @SerializedName("photos")
    lateinit var imageItem: ImageItem
}