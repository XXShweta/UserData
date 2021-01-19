package com.project.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.R
import com.project.databinding.ItemPostBinding
import com.project.services.model.response.Post
import com.project.ui.viewModel.ProjectMainViewModel
import kotlinx.android.synthetic.main.item_post.view.*

class MainFragmentListAdapter (private val postList: LinkedHashMap<Int, ArrayList<Post>>, private val projectMainViewModel: ProjectMainViewModel): RecyclerView.Adapter<MainFragmentListAdapter.PostListViewHolder> (){

    fun updatePostList(newPostList: LinkedHashMap<Int, ArrayList<Post>>){
        postList.clear()
        postList.putAll(newPostList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_post, parent, false)
        return PostListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val userDataKey = postList.keys.toIntArray()
        val keyValue = userDataKey[position]
        holder.binding.key = keyValue
        holder.itemView.userIdData.text = keyValue.toString()
        holder.itemView.numOfPostData.text = postList.get(keyValue)!!.size.toString()
    }

    inner class PostListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var binding : ItemPostBinding
        init {
            binding = ItemPostBinding.bind(itemView)
            binding.projectMainViewModel = projectMainViewModel
        }
    }
}