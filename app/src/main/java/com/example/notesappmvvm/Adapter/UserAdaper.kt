package com.example.notesappmvvm.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappmvvm.Model.User
import com.example.notesappmvvm.R

class UserAdaper(private val context: Context,private var userList:ArrayList<User>):RecyclerView.Adapter<UserAdaper.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_row,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user:User=userList[position]
        holder.name.text=user.name
        holder.age.text=user.age.toString()

    }

    fun setData(userList: ArrayList<User>)
    {
        this.userList=userList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =userList.size


    inner class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val name:TextView=itemView.findViewById(R.id.text_view_name)
        val age:TextView=itemView.findViewById(R.id.text_view_age)
    }
}