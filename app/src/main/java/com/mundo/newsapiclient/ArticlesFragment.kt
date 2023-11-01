package com.mundo.newsapiclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mundo.newsapiclient.data.util.Resource
import com.mundo.newsapiclient.databinding.FragmentArticlesBinding
import com.mundo.newsapiclient.presentation.adapter.ArticleAdapter
import com.mundo.newsapiclient.presentation.viewmodel.ClientViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ArticlesFragment : Fragment() {
    private lateinit var clientViewModel: ClientViewModel
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var binding: FragmentArticlesBinding
    // Basic pagination managements
    private var isLoading = false
    private var isScrolling = false
    private var isLastPage = false
    private var pages = 0
    // Query params
    private var page = 1
    private var country = "us"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticlesBinding.bind(view)
        // Pass adapter and viewModel objects from Activity to fragment reference variables
        (activity as MainActivity).apply {
            clientViewModel = viewModelM
            articleAdapter = articleAdapterM
        }
        initRecyclerView()
        viewList()
        setSearchView()
    }

    private fun viewList() {
        clientViewModel.getBusinessHeadlines(country, page)
        clientViewModel.businessHeadlines.observe(viewLifecycleOwner){ response ->
            when (response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        articleAdapter.differ.submitList(it.articles.toList())
                        pages = if(it.totalResults % 20 == 0) it.totalResults /20 else it.totalResults/20 + 1
                        isLastPage = page == pages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let{
                        Toast.makeText(activity,"There was an error: $it", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun initRecyclerView() {
        articleAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(
                R.id.action_articlesFragment_to_infoFragment,
                bundle
            )
        }
        binding.rvArticles.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@ArticlesFragment.onScrollListener)
        }
    }

    private fun showProgressBar(){
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }

    private val onScrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvArticles.layoutManager as LinearLayoutManager
            val currentListSize = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedTheEnd = topPosition+visibleItems >= currentListSize
            val shouldPaginate = !isLoading && !isLastPage && hasReachedTheEnd && isScrolling
            if(shouldPaginate)
                page++
            clientViewModel.getBusinessHeadlines(country, page)
            isScrolling = false
        }
    }

    // Search functionality
    private fun setSearchView(){
        binding.svBusiness.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                clientViewModel.getSearchedBusinessHeadlines(country, page, query = query.toString())
                viewSearchedList()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    clientViewModel.getSearchedBusinessHeadlines(country, page, query = newText.toString())
                    viewSearchedList()
                }
                return false
            }
        })

        binding.svBusiness.setOnCloseListener {
            initRecyclerView()
            viewSearchedList()
            false
        }
    }

    fun viewSearchedList(){
        clientViewModel.searchedHeadlines.observe(viewLifecycleOwner){ response ->
            when (response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        articleAdapter.differ.submitList(it.articles.toList())
                        pages = if(it.totalResults % 20 == 0) it.totalResults /20 else it.totalResults/20 + 1
                        isLastPage = page == pages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let{
                        Toast.makeText(activity,"There was an error: $it", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }
}