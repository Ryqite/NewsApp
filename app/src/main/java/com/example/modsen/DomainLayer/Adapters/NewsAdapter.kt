package com.example.modsen.DomainLayer.Adapters

import android.os.strictmode.UntaggedSocketViolation
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.modsen.DataLayer.Article
import com.example.modsen.R
import com.example.modsen.DataLayer.NewsItem
import com.example.modsen.databinding.NewsItemBinding
import okhttp3.EventListener

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    private lateinit var binding: NewsItemBinding
    inner class ArticleViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)
    private val differCallback=object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
           return oldItem==newItem
        }
    }

    val differ= AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_item,parent,false)
        )
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder,
        position: Int
    ) {
        val article=differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(binding.ivArticleImage)
            binding.tvSource.text=article.source.name
            binding.tvTitle.text=article.title
            binding.tvDescription.text=article.description
            setOnClickListener{
                //
                onItemClickListener?.let{it(article)}
            }
        }
    }
    override fun getItemCount(): Int {
        //
       return differ.currentList.size
    }
    private var onItemClickListener: ((Article)-> Unit)? =null
    fun setOnItemClickListener(listener: (Article)->Unit){
        //
        onItemClickListener= listener
    }
}