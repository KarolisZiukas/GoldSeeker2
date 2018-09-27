package com.example.karolis.logginhours.widgets

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DatePicker @Inject constructor(): DialogFragment(), DatePickerDialog.OnDateSetListener{

    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    lateinit var calendar: Calendar
    private lateinit var datePickerCallback: DatePickerCallback


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(context, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        datePickerCallback.onDateSelected(year, month, dayOfMonth)
    }



    interface DatePickerCallback{

        fun onDateSelected(year: Int, month: Int, dayOfMonth: Int)

    }

    fun setDatePickerCallback(callBack: DatePickerCallback){
        this.datePickerCallback = callBack
    }
}