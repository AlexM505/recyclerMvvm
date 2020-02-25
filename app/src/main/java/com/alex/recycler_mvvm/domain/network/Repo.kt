package com.alex.recycler_mvvm.domain.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alex.recycler_mvvm.model.User
import com.google.firebase.firestore.FirebaseFirestore

class Repo {

    fun getUserData():LiveData<MutableList<User>>{
        val mutableData = MutableLiveData<MutableList<User>>()

        FirebaseFirestore.getInstance().collection("Usuarios").get().addOnSuccessListener {result ->

            val listData = mutableListOf<User>()
            for(document in result){
                val imageUrl = document.getString("imageUrl")
                val nombre = document.getString("nombre")
                val descripcion = document.getString("descripcion")

                val usuario = User(
                    imageUrl!!,
                    nombre!!,
                    descripcion!!
                )
                listData.add(usuario)
            }

            mutableData.value = listData
        }

        return mutableData
    }
}