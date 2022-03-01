package com.ait.weatherapp.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.AdapterView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import com.ait.weatherapp.DetailsActivity
import com.ait.weatherapp.R
import com.ait.weatherapp.ScrollingActivity
import com.ait.weatherapp.adapter.CityRecyclerAdapter
import com.ait.weatherapp.data.City
import com.ait.weatherapp.databinding.CityDialogueBinding
import com.ait.weatherapp.databinding.CityRowBinding

class CityDialog: DialogFragment() {

    interface CityHandler {
        fun cityCreated(newItem: City)
    }

    lateinit var cityHandler: CityHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
           cityHandler = context as CityHandler
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    R.string.not_implementing_interface))
        }
    }


    lateinit var cityDialogueBinding: CityDialogueBinding
    lateinit var cityRowBinding: CityRowBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder = AlertDialog.Builder(requireContext())

        cityDialogueBinding = CityDialogueBinding.inflate(layoutInflater)
        dialogBuilder.setView(cityDialogueBinding.root)

        cityRowBinding = CityRowBinding.inflate(layoutInflater)
        dialogBuilder.setPositiveButton(getString(R.string.ok)) { dialog, which ->
            cityRowBinding.tvCity.text = cityDialogueBinding.etCityName.text
        }

        dialogBuilder.setNegativeButton(getString(R.string.cancel)) { dialog, which ->
            dialog.cancel()
        }

        return dialogBuilder.create()

    }

    override fun onResume() {
        super.onResume()

        val dialog = dialog as AlertDialog
        val positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if(cityDialogueBinding.etCityName.text.isEmpty())
            {
                cityDialogueBinding.etCityName.error = getString(R.string.enterCity)
            } else{
                handleItemCreate()
            }
            dialog.dismiss()
            }
        }

    private fun handleItemCreate() {
        cityHandler.cityCreated(
            City(
                cityDialogueBinding.etCityName.text.toString()
            )
        )
    }



    }