package com.frank.expensestracker.extensions

import android.app.Activity
import com.frank.expensestracker.ExpenseTrackerApplication

val Activity.expenseTrackerApplication : ExpenseTrackerApplication
get() = application as ExpenseTrackerApplication