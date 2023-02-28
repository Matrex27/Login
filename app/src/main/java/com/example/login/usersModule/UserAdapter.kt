package com.example.login.usersModule

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.login.R
import com.example.login.common.entities.User
import com.example.login.databinding.ItemUserBinding


class UserAdapter() : ListAdapter<User, ViewHolder>(UserDiffCallBack()) {

    private lateinit var mContext: Context

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUserBinding.bind(view)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false)

        return viewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)

        with(holder as viewHolder) {

            Glide.with(mContext)
                .load(user.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.userAvatar)

            binding.tvEmail.text = user.email
            binding.tvFullName.text = user.showFullName()
            binding.tvId.text = user.id.toString()
        }
    }


    class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }
}