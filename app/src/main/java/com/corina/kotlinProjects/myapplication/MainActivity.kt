package com.corina.kotlinProjects.myapplication

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.corina.kotlinProjects.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verknüpftt das XML-Layout mit dieser Klasse
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDatePicker.setOnClickListener { view ->
            onClickDatePicker(view)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onClickDatePicker(view: View) {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            this,

            { view, year, month, dayOfMonth ->

                var selectedDate = "$dayOfMonth.${month + 1}.$year"
                /* Ausgewähltes Datum wird im Textfeld als Text gesetzt */

                binding.tvSelectedDate.text = selectedDate




                val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN)

                val sdfDate = sdf.parse(selectedDate)

                val selectedDateToMinutes = sdfDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

                binding.tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

            }, year, month, day
        )
        dpd.datePicker.maxDate = Date().time

        dpd.show()
    }
}
