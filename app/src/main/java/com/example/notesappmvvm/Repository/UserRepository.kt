package com.example.notesappmvvm.Repository

import android.content.Context
import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import com.example.notesappmvvm.Database.UserDataBase
import com.example.notesappmvvm.Model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {

    companion object {

        private var userDataBase:UserDataBase?=null

        fun initializeDB(context: Context):UserDataBase?{

            return UserDataBase.getInstance(context)

        }

        fun insert(context: Context,user: User) {
            userDataBase= initializeDB(context)

            CoroutineScope(IO).launch {
                userDataBase?.getDao()?.insert(user)
            }

        }

        fun getAllUserData(context: Context): LiveData<List<User>>? {
            userDataBase= initializeDB(context)
            return userDataBase?.getDao()?.getAllUserData()

        }
    }
}