package com.shilo.msapps.adapter

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.shilo.msapps.R
import com.shilo.msapps.databinding.FragmentItemBinding
import com.shilo.msapps.view.ItemFragment

const val IMAGE_URL = "image_url"
class MyItemRVAdapter(
    private val fragment: ItemFragment,
    private val values: List<String>
) : RecyclerView.Adapter<MyItemRVAdapter.ViewHolder>()
      {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        var bundle : Bundle
        Glide.with(fragment)
            .load(item)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imageView)
        holder.imageView.setOnClickListener{
            bundle = bundleOf(IMAGE_URL to item)
            fragment.findNavController().navigate(R.id.action_itemFragment_to_imageFragment, bundle)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.imageView
    }


}