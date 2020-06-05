package com.frank.expensestracker.ui.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frank.expensestracker.R
import com.frank.expensestracker.databinding.FragmentCategoriesBinding
import com.frank.expensestracker.db.entities.Category
import com.frank.expensestracker.di.ViewModelFactory
import com.frank.expensestracker.extensions.expenseTrackerApplication
import com.frank.expensestracker.ui.category.CategoriesFragmentDirections.ActionCategoriesFragmentToCategoryFragment
import com.frank.expensestracker.ui.category.adapters.CategoriesAdapter
import com.frank.expensestracker.utils.EventObserver
import javax.inject.Inject

class CategoriesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CategoriesViewModel

    private lateinit var dataBinding: FragmentCategoriesBinding

    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = FragmentCategoriesBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.viewModel = viewModel

        viewModel.addNewCategory.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                navigateToCategoryPage(-1)
            }
        })

        initRecycleView()

        viewModel.categories.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return dataBinding.root
    }

    private fun initRecycleView() {

        val manager = LinearLayoutManager(requireActivity())
        dataBinding.rcvCategories.layoutManager = manager

        val itemDividerItemDecoration =
            DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL)
        itemDividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.line_divider))
        dataBinding.rcvCategories.addItemDecoration(itemDividerItemDecoration)

        val itemEventListener = object : CategoriesAdapter.ItemEventListener {
            override fun deleteCategory(category: Category) {
                viewModel.deleteCategory(category)
            }

            override fun editCategory(category: Category) {
                navigateToCategoryPage(category.id)
            }
        }

        adapter = CategoriesAdapter(CategoriesAdapter.ItemEventHandler(itemEventListener))
        dataBinding.rcvCategories.adapter = adapter


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        expenseTrackerApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CategoriesViewModel::class.java)
    }

    private fun navigateToCategoryPage(category_id: Int) {
        val action = CategoriesFragmentDirections.actionCategoriesFragmentToCategoryFragment(category_id)
        this.findNavController()
            .navigate(action)
    }

}
