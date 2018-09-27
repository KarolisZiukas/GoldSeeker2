package com.example.karolis.logginhours.widgets

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.widget.TimePicker
import java.util.*
import javax.inject.Inject

class CustomTimePicker @Inject constructor(): DialogFragment(), TimePickerDialog.OnTimeSetListener {

    lateinit var timePickerCallback: TimePickerCallback

    interface TimePickerCallback {
        fun onTimeSelected(hourOfDay: Int, minute: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        timePickerCallback.onTimeSelected(hourOfDay, minute)
    }

    fun setCallback(timePickerCallback: TimePickerCallback) {
        this.timePickerCallback = timePickerCallback
    }


}