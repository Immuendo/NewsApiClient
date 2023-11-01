package com.mundo.newsapiclient.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mundo.newsapiclient.data.model.Article
import com.mundo.newsapiclient.databinding.ArticleItemBinding

class ArticleAdapter() : RecyclerView.Adapter<ArticleAdapter.MyViewHolder>() {
    private var onItemClickListener: ((Article) -> Unit)? = null
    private val callback = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class MyViewHolder(
        private val binding: ArticleItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(article: Article) {
                binding.tvTitle.text = article.title
                binding.tvDescription.text = article.description
                binding.tvPublishedAt.text = article.publishedAt
                binding.tvSource.text = article.source?.name

                Glide.with(binding.ivArticleImage.context)
                    .load(article.urlToImage)
                    .optionalFitCenter()
                    .into(binding.ivArticleImage)

                binding.root.setOnClickListener {
                    onItemClickListener?.let {
                        it(article)
                    }
                }
            }
    }

    fun setOnItemClickListener(listener: ((Article) -> Unit)) {
        onItemClickListener = listener
    }
}