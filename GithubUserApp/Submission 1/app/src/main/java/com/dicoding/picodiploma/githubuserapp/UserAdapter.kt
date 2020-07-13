package com.dicoding.picodiploma.githubuserapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.dicoding.picodiploma.githubuserapp.UserAdapter.RecyclerViewHolder

class UserAdapter (private val users: ArrayList<User>) : Adapter<RecyclerViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val user = users[position]
        user.avatar?.let { holder.ivAvatar.setImageResource(it) }
        holder.tvName.text = user.name
        holder.tvUsername.text = user.username
        holder.tvCompany.text = user.company

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(users[holder.adapterPosition]) }
    }

    inner class RecyclerViewHolder (itemView: View) : ViewHolder(itemView) {
        var ivAvatar: ImageView = itemView.findViewById(R.id.iv_item_avatar)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvUsername: TextView = itemView.findViewById(R.id.tv_item_username)
        var tvCompany: TextView = itemView.findViewById(R.id.tv_item_company)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}