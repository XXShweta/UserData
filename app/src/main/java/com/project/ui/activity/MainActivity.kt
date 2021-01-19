package com.project.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.project.R
import com.project.databinding.ActivityMainBinding
import com.project.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(){

    private var navController: NavController?= null

    override fun getBindingVariable(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        navController = Navigation.findNavController(this, R.id.main_fragment)
        setupNavigation()
    }

    private fun setupNavigation() {
        val topLevelDestination = HashSet<Int>()
        topLevelDestination.add(R.id.projectMainFrgament)
    }
}
