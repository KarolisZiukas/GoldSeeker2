package com.example.karolis.logginhours.widgets

class Generator {

    companion object {
        @JvmStatic
        fun generateId(): Long {
            return System.nanoTime()
        }

        @JvmStatic
        fun generateIdInt(): Int {
            return System.nanoTime().toInt()
        }
    }
}