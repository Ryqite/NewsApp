package com.example.modsen.DomainLayer.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.modsen.DataLayer.Article
import com.example.modsen.R

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
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
            Glide.with(this).load(article.urlToImage).into(findViewById(R.id.ivArticleImage))
            val source:TextView=findViewById(R.id.tvSource)
            val title:TextView=findViewById(R.id.tvTitle)
            val description:TextView=findViewById(R.id.tvDescription)
            source.text=article.source.name
            title.text=article.title
            description.text=article.description
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