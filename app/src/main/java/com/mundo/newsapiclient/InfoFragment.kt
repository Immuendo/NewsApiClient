package com.mundo.newsapiclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mundo.newsapiclient.databinding.FragmentInfoBinding
import com.mundo.newsapiclient.presentation.viewmodel.ClientViewModel

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private lateinit var clientViewModel: ClientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        clientViewModel = (activity as MainActivity).viewModelM
        val args: InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        binding.wvInfo.apply {
            webViewClient = WebViewClient()
            if(!article.url.isNullOrEmpty())
                loadUrl(article.url )
        }
        binding.fabSave.setOnClickListener{
            clientViewModel.saveBusinessArticle(article)
            Snackbar.make(view, "Saved Successfully! Article id: ${article.id}", Snackbar.LENGTH_LONG).show()
        }
    }

}