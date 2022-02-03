package com.shilo.msapps.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.shilo.msapps.R
import kotlinx.android.synthetic.main.fragment_image.*

const val IMAGE_URL = "image_url"
class ImageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.get(IMAGE_URL) != null) {
            Glide.with(view)
                .load(arguments!!.getString(IMAGE_URL))
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
        }
    }
}