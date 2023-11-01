package com.mundo.newsapiclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mundo.newsapiclient.databinding.ActivityMainBinding
import com.mundo.newsapiclient.presentation.adapter.ArticleAdapter
import com.mundo.newsapiclient.presentation.viewmodel.ClientViewModel
import com.mundo.newsapiclient.presentation.viewmodel.ClientViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint()
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factoryM: ClientViewModelFactory
    @Inject
    lateinit var articleAdapterM: ArticleAdapter
    lateinit var viewModelM : ClientViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModelM = ViewModelProvider(this, factoryM)[ClientViewModel::class.java]
        // Setup navController to pass to Bottom Nav View method
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvBusiness.setupWithNavController(navController)
        setContentView(binding.root)
    }
}