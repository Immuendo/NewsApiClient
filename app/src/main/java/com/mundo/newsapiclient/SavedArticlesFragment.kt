package com.mundo.newsapiclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mundo.newsapiclient.databinding.FragmentSavedArticlesBinding
import com.mundo.newsapiclient.presentation.adapter.ArticleAdapter
import com.mundo.newsapiclient.presentation.viewmodel.ClientViewModel


class SavedArticlesFragment : Fragment() {
    private lateinit var binding: FragmentSavedArticlesBinding
    private lateinit var fragmentViewModel: ClientViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedArticlesBinding.bind(view)
        // Pass adapter and viewModel objects from Activity to fragment reference variables
        (activity as MainActivity).apply {
            fragmentViewModel = viewModelM
            articleAdapter = articleAdapterM
        }
        initRecyclerView()
        fragmentViewModel.getSavedArticles().observe(viewLifecycleOwner) {
            articleAdapter.differ.submitList(it)
        }

        val itemTouchHelperCallback = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = articleAdapter.differ.currentList[position]
                fragmentViewModel.deleteSavedArticle(article)
                Snackbar.make(view, "Deleted Successfully", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo"){
                            fragmentViewModel.saveBusinessArticle(article)
                        }
                    }.show()
            }

        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSavedHeadlines)
        }
    }

    private fun initRecyclerView() {
        articleAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(
                R.id.action_savedArticlesFragment_to_infoFragment,
                bundle
            )
        }
        binding.rvSavedHeadlines.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}