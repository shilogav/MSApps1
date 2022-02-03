package com.shilo.msapps.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shilo.msapps.model.JsonPlaceHolderApi
import com.shilo.msapps.model.Photos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.shilo.msapps.model.ImageDetails
import com.shilo.msapps.model.ImageItem


const val TAG = "repository"
class Repository {
    val itemMutableLiveData = MutableLiveData<Array<String>>()
    suspend fun getArray() {
        retrofitCall()
    }

    private fun retrofitCall() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/?")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val call = jsonPlaceHolderApi.getPhotos()
        call.enqueue(object : Callback<Photos> {
            override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                if(!response.isSuccessful) {
                    Log.i(TAG, response.code().toString())
                    throw java.lang.RuntimeException(response.code().toString())
                }
                val item = response.body()
                val photos = item?.imageItem?.photo
                val array = photos?.let { convertPojoNumberToString(it) }
                if (item != null) {
                    itemMutableLiveData.postValue(array!!)
                }
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
                t.message?.let { Log.i(TAG, it)
                    throw RuntimeException(it)}
            }

        })
    }
    private fun convertPojoNumberToString(urlStrings: Array<ImageDetails>): Array<String> {
        val array : Array<String> = Array(urlStrings.size) {""}
        urlStrings.forEachIndexed { index, element ->
            array[index] = element.url
        }
        return array
    }

}