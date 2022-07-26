package com.example.freshnews


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAddapter( private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.newsitem,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{

            listener.onItemClicked(items[viewHolder.bindingAdapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val currentItem = items[position]
        holder.titleView.text = currentItem.title
    }


 fun updateNews(updatedNews: ArrayList<News>){
     items.clear()
     items.addAll(updatedNews)

     notifyDataSetChanged()
 }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val  titleView: TextView = itemView.findViewById(R.id.item)
}
interface  NewsItemClicked{
    fun onItemClicked(item : News)
}