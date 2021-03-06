package com.example.notesappmvvm.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notesappmvvm.Model.User
import com.example.notesappmvvm.Repository.UserRepository

class UserViewModel :ViewModel() {

    fun insert(context: Context,user:User)
    {
        UserRepository.insert(context, user)
    }

    fun getAllUserData(context: Context):LiveData<List<User>>?
    {
        return UserRepository.getAllUserData(context)
    }
}