package com.shilo.msapps.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.shilo.msapps.R
import com.shilo.msapps.adapter.MyItemRVAdapter
import com.shilo.msapps.viewmoldel.MainViewModel
import java.util.ArrayList
import kotlin.system.measureTimeMillis


/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {
    private val fragment = this
    private lateinit var viewModel: MainViewModel
    private val items = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        if (view is RecyclerView) {
            with(view) {
                adapter = MyItemRVAdapter(fragment, items)
                view.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL,false)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val executionTime = measureTimeMillis {
            viewModel.itemMutableLiveData.observe(this, {
                items.addAll(it)
                if (view is RecyclerView){
                    with(view) {
                        adapter?.notifyDataSetChanged()
                    }
                }
            })
        }
    }
}