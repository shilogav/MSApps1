package com.shilo.msapps.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonPlaceHolderApi {
    @GET("services/rest/" +
            "?api_key=aabca25d8cd75f676d3a74a72dcebf21&nojsoncallback=1" +
            "&format=json&extras=url_s&method=flickr.photos.getRecent")
    fun getPhotos() : Call<Photos>
}
