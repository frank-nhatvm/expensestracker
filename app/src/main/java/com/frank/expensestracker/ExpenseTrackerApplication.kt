package com.frank.expensestracker

import android.app.Application
import com.frank.expensestracker.di.DaggerAppComponent

open class ExpenseTrackerApplication: Application() {

    open val appComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}