package com.ait.weatherapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ait.weatherapp.DetailsActivity
import com.ait.weatherapp.R
import com.ait.weatherapp.ScrollingActivity
import com.ait.weatherapp.data.City
import com.ait.weatherapp.databinding.CityRowBinding
import com.ait.weatherapp.dialog.CityDialog
import kotlin.concurrent.thread

class CityRecyclerAdapter(private var cityList: MutableList<City>): RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder>(){

    lateinit var cityRowBinding: CityRowBinding

    lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        cityRowBinding = CityRowBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ViewHolder(cityRowBinding.cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = cityList[position].getCityName()

        viewHolder.btnDetails.setOnClickListener {
            context = viewHolder.textView.context

            val intentDetails = Intent()
            intentDetails.setClass(
                this.context,
                DetailsActivity::class.java
            )
            intentDetails.putExtra(
                ScrollingActivity.KEY_DATA,
                viewHolder.textView.text.toString()
            )

            startActivity(context,intentDetails,null)
        }

        viewHolder.btnDelete.setOnClickListener {
            deleteItemBasedOnPosition(position)
        }
    }

    override fun getItemCount() = cityList.size

    fun addCity(city: City) {
        cityList += city
        notifyItemInserted(cityList.lastIndex)
    }

    fun deleteItemBasedOnPosition(position: Int) {
        cityList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvCity)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
        val btnDetails: Button = view.findViewById(R.id.btnDetails)
    }



}