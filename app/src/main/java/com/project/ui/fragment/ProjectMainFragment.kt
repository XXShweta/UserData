package com.project.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.BR
import com.project.R
import com.project.databinding.MainProjectFragmentBinding
import com.project.di.factory.DaggerViewModelFactory
import com.project.ui.adapter.MainFragmentListAdapter
import com.project.ui.base.BaseFragment
import com.project.ui.navigator.ProjectMainNavigator
import com.project.ui.viewModel.ProjectMainViewModel
import kotlinx.android.synthetic.main.main_project_fragment.*
import javax.inject.Inject

class ProjectMainFragment: BaseFragment<MainProjectFragmentBinding,ProjectMainViewModel> (), ProjectMainNavigator{

    @Inject
    lateinit var providerFactory: DaggerViewModelFactory
    private lateinit var  mainFragmentListAdapter: MainFragmentListAdapter
    val mViewModel: ProjectMainViewModel by lazy {
        ViewModelProvider(this, providerFactory)[ProjectMainViewModel::class.java]
    }

    override val bindingVariable: Int
        get() = BR.projectMainViewModel

    override val layoutId: Int
        get() = R.layout.main_project_fragment

    override fun getViewModel(): ProjectMainViewModel {
        mViewModel.setNavigator(this)
        return mViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragmentListAdapter = MainFragmentListAdapter(
            LinkedHashMap(), mViewModel)
        listOfPostRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainFragmentListAdapter
        }
        if(mViewModel.hashMap.size <=0){
            mViewModel.isLoading.value = true
            mViewModel.getPostFromApi()
        }
        observingLivedata()
    }
    private fun observingLivedata(){
        mViewModel.isLoading.observe(this, Observer { it->
            if(!it){
                mainFragmentListAdapter.updatePostList(mViewModel.hashMap)
            }
        })
    }

    override fun onError(error: String) {
        Toast.makeText(context,error,Toast.LENGTH_SHORT).show()
    }

    override fun goToDetailScreen() {
        findNavController().navigate(R.id.action_projectMainFrgament_to_projectDetailFragment)
    }

}
