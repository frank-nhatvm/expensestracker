package com.frank.expensestracker.utils

import org.threeten.bp.OffsetDateTime


object Util {

    fun getStringOfDate(date: OffsetDateTime): String {
        return if (date.dayOfMonth == OffsetDateTime.now().dayOfMonth) {
            "Today"
        } else {
            "${date.dayOfMonth}/${date.month}/${date.year}"
        }
    }
}