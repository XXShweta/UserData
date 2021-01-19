package com.project.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.BR
import com.project.R
import com.project.databinding.MainProjectDetailFragmentBinding
import com.project.databinding.MainProjectFragmentBinding
import com.project.di.factory.DaggerViewModelFactory
import com.project.ui.adapter.DetailListAdapter
import com.project.ui.base.BaseFragment
import com.project.ui.navigator.ProjectDetailNavigator
import com.project.ui.navigator.ProjectMainNavigator
import com.project.ui.viewModel.ProjectDetailViewModel
import com.project.ui.viewModel.ProjectMainViewModel
import kotlinx.android.synthetic.main.main_project_detail_fragment.*
import javax.inject.Inject


class ProjectDetailFragment : BaseFragment<MainProjectDetailFragmentBinding, ProjectDetailViewModel>(),
    ProjectDetailNavigator {

    @Inject
    lateinit var providerFactory: DaggerViewModelFactory

    private lateinit var  mDetailListAdapter: DetailListAdapter

    val mViewModel: ProjectDetailViewModel by lazy {
        ViewModelProvider(this, providerFactory)[ProjectDetailViewModel::class.java]
    }

    override val bindingVariable: Int
        get() = BR.projectDetailViewModel

    override val layoutId: Int
        get() = R.layout.main_project_detail_fragment

    override fun getViewModel(): ProjectDetailViewModel {
        mViewModel.setNavigator(this)
        return mViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDetailListAdapter = DetailListAdapter(ArrayList())
        userPostsRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mDetailListAdapter
        }
        mViewModel.getUserDetailFromApi()
        observingLivedata()
    }

    private fun observingLivedata(){
        mViewModel.isLoading.observe(this, Observer { it->
            if(!it){
                mDetailListAdapter.updatePostList(mViewModel.getListOfPost())
            }
        })
    }

    override fun onError(error: String) {
        Toast.makeText(context,error, Toast.LENGTH_SHORT).show()
    }

    override fun setUIData() {

    }


}
