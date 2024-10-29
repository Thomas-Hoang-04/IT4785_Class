package com.example.listsearch

import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.RecyclerView
import com.example.listsearch.recyclerview.StudentAdapter
import com.example.listsearch.recyclerview.StudentData
import com.example.listsearch.recyclerview.StudentList
import com.google.android.material.search.SearchView

class MenuWatcher(private val searchView: SearchView, private val recyclerView: RecyclerView): TextWatcher {
    val refData = StudentList().list

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.toString().length < 2) {
            val emailAdapter = StudentAdapter(refData)
            recyclerView.adapter = emailAdapter
            return
        }
        val q: String = s.toString()
        val filteredList: List<StudentData> = refData.filter {
            if (q.any { it.isDigit() }) it.id.toString().contains(q, true)
            else it.name.contains(q, true)
        }
        val emailAdapter = StudentAdapter(filteredList)
        recyclerView.adapter = emailAdapter
    }

    override fun afterTextChanged(s: Editable?) {
    }
}