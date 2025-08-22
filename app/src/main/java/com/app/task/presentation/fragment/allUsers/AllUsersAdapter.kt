package com.app.task.presentation.fragment.allUsers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.task.data.local.entity.UserEntity
import com.app.task.databinding.ItemUserBinding

class AllUsersAdapter : ListAdapter<UserEntity, AllUsersAdapter.MyHolder>(ItemDiff) {

    private lateinit var fullList: ArrayList<UserEntity>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun setData(list: List<UserEntity>?) {
        submitList(list)
        fullList = ArrayList(list ?: emptyList())
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.itemBinding.tvName.text = item.name
            holder.itemBinding.tvJobTitle.text = item.jobTitle
            holder.itemBinding.tvGender.text = item.gender
            holder.itemBinding.tvAge.text = item.age.toString()
        }
    }

    inner class MyHolder(val itemBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private object ItemDiff : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }
}
