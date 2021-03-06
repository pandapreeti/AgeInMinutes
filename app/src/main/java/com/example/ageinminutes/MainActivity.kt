package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)

        }
    }


    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()

        val year =myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this,"The chosen year is $selectedYear , month is $selectedMonth and day is $dayOfMonth",Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val getDate = sdf.parse(selectedDate)

            val selectedDateInMin = getDate!!.time /60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMin = currentDate!!.time/60000

            val differenceInDates = currentDateInMin - selectedDateInMin
            tvSelectedDateInMinutes.setText(differenceInDates.toString())

        },
        year,
        month,
        dayOfMonth)

        dpd.datePicker.setMaxDate(Date().time- 8640000)
        dpd.show()


    }
}