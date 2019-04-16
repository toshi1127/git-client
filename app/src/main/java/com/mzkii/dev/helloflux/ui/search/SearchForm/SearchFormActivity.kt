package com.mzkii.dev.helloflux.ui.search.SearchForm

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.mzkii.dev.helloflux.R
import kotlinx.android.synthetic.main.search_form_activity.*

class SearchFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_form_activity)
        initView()
    }

    private fun initView() {
        val items = arrayOf("NM", "NY", "NC", "ND")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner1.adapter = adapter
    }
}