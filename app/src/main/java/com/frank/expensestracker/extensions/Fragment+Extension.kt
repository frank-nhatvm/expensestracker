package com.frank.expensestracker.extensions

import androidx.fragment.app.Fragment
import com.frank.expensestracker.ExpenseTrackerApplication

val Fragment.expenseTrackerApplication: ExpenseTrackerApplication
    get() = requireActivity().expenseTrackerApplication