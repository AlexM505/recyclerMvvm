package com.alex.recycler_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.recycler_mvvm.viewmodel.MainViewModel
import com.alex.recycler_mvvm.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) } //agregar dependencia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter(this)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

//        val dummyList = mutableListOf<User>()
//        dummyList.add(User("https://softwareengineeringdaily.com/wp-content/uploads/2018/10/kotlin.jpg",
//            "Android", "MVVM con kotlin y firebase"))
//        dummyList.add(User("https://blog.hdwallsource.com/wp-content/uploads/2018/01/twitch-icon-logo-wallpaper-62702-64683-hd-wallpapers.jpg",
//            "Twitch","MVVM con kotlin y firebase"))
//        dummyList.add(User("https://cernisoftgaming.com/wp-content/uploads/2020/02/Fortnite-1024x615.jpg",
//            "Fortnite","MVVM con kotlin y firebase"))
//        adapter.setListData(dummyList)
//        adapter.notifyDataSetChanged()

        observeData()

    }

    fun observeData(){
        shimmer_view_container.startShimmer()
        viewModel.fetchUserdata().observe(this, Observer {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}
