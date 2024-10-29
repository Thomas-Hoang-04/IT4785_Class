package com.example.listsearch

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listsearch.recyclerview.StudentAdapter
import com.example.listsearch.recyclerview.StudentData
import com.example.listsearch.recyclerview.StudentList
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val refData: List<StudentData> = StudentList().list

        val searchBar = findViewById<SearchBar>(R.id.searchBar)
        searchBar.inflateMenu(R.menu.search_bar_menu)

        val searchView: SearchView = findViewById(R.id.searchView)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recyclerSearchView = findViewById<RecyclerView>(R.id.recycler_view_result)
        recyclerSearchView.layoutManager = LinearLayoutManager(this)

        val emailAdapter = StudentAdapter(refData)
        recyclerView.adapter = emailAdapter

        searchView.editText.addTextChangedListener(MenuWatcher(searchView, recyclerSearchView))
    }
}