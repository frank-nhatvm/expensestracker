package com.frank.expensestracker.ui.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.frank.expensestracker.R
import com.frank.expensestracker.di.ViewModelFactory
import com.frank.expensestracker.extensions.expenseTrackerApplication
import javax.inject.Inject

class CategoriesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        expenseTrackerApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this,viewModelFactory).get(CategoriesViewModel::class.java)
    }

}
