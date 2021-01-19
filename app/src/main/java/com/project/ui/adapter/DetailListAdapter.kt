package com.project.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.R
import com.project.databinding.ItemDetailUserBinding
import com.project.databinding.ItemPostBinding
import com.project.services.model.response.Post

class DetailListAdapter(private val list : ArrayList<Post>): RecyclerView.Adapter<DetailListAdapter.DetailListViewHolder> ()  {


    fun updatePostList(newPostList: ArrayList<Post>){
        list.clear()
        list.addAll(newPostList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_detail_user, parent, false)
        return DetailListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
      return  list.size
    }

    override fun onBindViewHolder(holder: DetailListViewHolder, position: Int) {
       holder.binding.post = list[position]
    }

    inner class DetailListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var binding : ItemDetailUserBinding
        init {
            binding = ItemDetailUserBinding.bind(itemView)
        }
    }
}