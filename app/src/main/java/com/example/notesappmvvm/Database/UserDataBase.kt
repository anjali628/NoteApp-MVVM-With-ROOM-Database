package com.example.notesappmvvm.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesappmvvm.Dao.UserDao
import com.example.notesappmvvm.Model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase:RoomDatabase() {

    abstract fun getDao():UserDao

    companion object{
        private const val DATABASE_NAME="UserDatabase"

        @Volatile
        var instance:UserDataBase?=null

        fun getInstance(context: Context):UserDataBase?{

            if (instance==null)
            {
                synchronized(UserDataBase::class.java)
                {
                    if (instance==null)
                    {
                        instance=Room.databaseBuilder(context,UserDataBase::class.java,
                        DATABASE_NAME)
                            .build()
                    }
                }
            }

            return instance
        }
    }

}