package com.ait.weatherapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ait.weatherapp.adapter.CityRecyclerAdapter
import com.ait.weatherapp.data.City
import com.ait.weatherapp.databinding.ActivityScrollingBinding
import com.ait.weatherapp.databinding.CityDialogueBinding
import com.ait.weatherapp.databinding.CityRowBinding
import com.ait.weatherapp.dialog.CityDialog
import com.bumptech.glide.Glide

class ScrollingActivity : FragmentActivity(),CityDialog.CityHandler {

    companion object {
        const val KEY_DATA = "KEY_DATA"  //static field, prevent typos
        const val CITY_DIALOG = "CITY_DIALOG"
    }


    private val cities = mutableListOf(
        City("Budapest"),
        City("Chicago"),
        City("Tokyo"),
        City("New York"),
        City("California")
    )
    private lateinit var cityAdapter: CityRecyclerAdapter

    private lateinit var scrollingbinding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scrollingbinding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(scrollingbinding.root)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerItem)
        cityAdapter = CityRecyclerAdapter(cities)
        recyclerView.adapter = cityAdapter

        scrollingbinding.fab.setOnClickListener { view ->
            CityDialog().show(supportFragmentManager, CITY_DIALOG)
        }


    }

    override fun cityCreated(newItem: City) {
        cityAdapter.addCity(newItem)
    }
}