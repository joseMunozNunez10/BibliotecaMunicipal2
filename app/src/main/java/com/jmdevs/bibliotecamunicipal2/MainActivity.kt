package com.jmdevs.bibliotecamunicipal2

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.books_recycler_view)
        val searchEditText = findViewById<TextInputEditText>(R.id.search_input_edit_text)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        val adapter = BookAdapter()
        recyclerView.adapter = adapter

        searchEditText.addTextChangedListener {
            viewModel.searchBooks(it.toString())
        }

        viewModel.books.observe(this) { books ->
            adapter.submitList(books)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}
