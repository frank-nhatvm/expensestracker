package com.frank.expensestracker.ui.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.frank.expensestracker.R
import com.frank.expensestracker.databinding.FragmentCategoryBinding
import com.frank.expensestracker.di.ViewModelFactory
import com.frank.expensestracker.extensions.expenseTrackerApplication
import com.frank.expensestracker.utils.EventObserver
import javax.inject.Inject

class CategoryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CategoryViewModel

    private lateinit var dataBinding: FragmentCategoryBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        dataBinding = FragmentCategoryBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel

        viewModel.backToCategoriesPage.observe(viewLifecycleOwner,EventObserver{
            if(it){
                this.findNavController().popBackStack()
            }
        })

        val args = CategoryFragmentArgs.fromBundle(requireArguments())
        val categoryId = args.categoryId
        if(categoryId != -1){
            viewModel.getCategoryById(categoryId)
        }

        return dataBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        expenseTrackerApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this,viewModelFactory).get(CategoryViewModel::class.java)
    }
}
