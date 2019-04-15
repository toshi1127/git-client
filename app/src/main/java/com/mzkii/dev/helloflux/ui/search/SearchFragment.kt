package com.mzkii.dev.helloflux.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mzkii.dev.helloflux.R
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.search_activity.view.*


class SearchFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.search_activity, container, false)
        val fab: FloatingActionButton = view.fab
        fab.setOnClickListener { v -> Toast.makeText(v.context, "FABが押されました", Toast.LENGTH_SHORT).show() }
        return inflater.inflate(R.layout.search_activity, container, false)
    }
}