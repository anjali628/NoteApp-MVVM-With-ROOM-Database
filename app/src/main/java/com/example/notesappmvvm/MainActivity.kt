package com.example.notesappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappmvvm.Adapter.UserAdaper
import com.example.notesappmvvm.Model.User
import com.example.notesappmvvm.ViewModel.UserViewModel
import com.example.notesappmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var builder:AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var name:EditText
    private lateinit var age:EditText
    private lateinit var saveBtn:Button
    private lateinit var userAdapter: UserAdaper

    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userAdapter= UserAdaper(this,ArrayList<User>())
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=userAdapter
        }
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getAllUserData(applicationContext)?.observe(this, Observer {
            userAdapter.setData(it as ArrayList<User>)

        })
        binding.floatingActionBtn.setOnClickListener {
            openDialog()
        }

    }

    private fun openDialog() {

        builder=AlertDialog.Builder(this)
        var itemView:View=LayoutInflater.from(applicationContext).inflate(R.layout.dialog,null)
        dialog=builder.create()
        dialog.setView(itemView)

        name=itemView.findViewById(R.id.edit_text_name)
        age=itemView.findViewById(R.id.edit_text_age)
        saveBtn=itemView.findViewById(R.id.bt_save)

        saveBtn.setOnClickListener {
            saveDataIntoRoomDatabase()
        }

        dialog.show()

    }

    private fun saveDataIntoRoomDatabase() {
        val getName = name.text.toString().trim()
        val getAge=age.text.toString().trim()

        if(!TextUtils.isEmpty(getName) && !TextUtils.isEmpty(getAge))
        {
            userViewModel.insert(this, User(getName,Integer.parseInt(getAge)))
            dialog.dismiss()
        }
        else{
            Toast.makeText(applicationContext,"Please fill all the fields",Toast.LENGTH_SHORT).show()
        }
    }
}