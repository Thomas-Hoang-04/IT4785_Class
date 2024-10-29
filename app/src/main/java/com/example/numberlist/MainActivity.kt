package com.example.numberlist

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView: ListView = findViewById(R.id.num_list)
        val error: TextView = findViewById(R.id.error)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val numText: EditText = findViewById(R.id.num)
        var state: NumState? = null

        val submit: Button = findViewById(R.id.show)

        submit.setOnClickListener {
            val text = numText.text.toString()
            if (!text.isDigitsOnly() || state == null) {
                error.visibility = View.VISIBLE
                error.text = getString(R.string.error)
                return@setOnClickListener
            }
            error.visibility = View.GONE
            val num = text.toLong()
            when (state) {
                NumState.ODD -> {
                    val list = (0..num).filter { it % 2 != 0L }
                    listView.adapter = ArrayAdapter(this, R.layout.list_item, list)
                }
                NumState.EVEN -> {
                    val list = (0..num).filter { it % 2 == 0L }
                    listView.adapter = ArrayAdapter(this, R.layout.list_item, list)
                }
                NumState.SQUARE -> {
                    val list = (0..num).map { it * it }
                    listView.adapter = ArrayAdapter(this, R.layout.list_item, list)
                }
                else -> {
                    error.visibility = View.VISIBLE
                    error.text = getString(R.string.error)
                }
            }
        }

        radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.radioOdd -> {
                    state = NumState.ODD
                }
                R.id.radioEven -> {
                    state = NumState.EVEN
                }
                R.id.radioSquare -> {
                    state = NumState.SQUARE
                }
            }
        }
    }
}