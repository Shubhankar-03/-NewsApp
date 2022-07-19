package com.example.freshnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest


class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var madapter :NewsAddapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  recyclerView = findViewById<RecyclerView>(R.id.recyclerVIew)

        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchdata()
        madapter = NewsAddapter(this)

        recyclerView.adapter= madapter


    }
    private  fun fetchdata() {
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=1bda88b5310a41b0a1b0da49dc5f1a1c"
        val jsonObjectRequest = JsonObjectRequest (
            Request.Method.GET,
            url,
            null,
            {
               val newsJsonArray = it.getJSONArray("articles")
               val newsArray = ArrayList<News>()
               for (i in 0 until newsJsonArray.length() ) {
                   val newsJSONObject = newsJsonArray.getJSONObject(i)
                   val news = News (
                       newsJSONObject.getString("title"),
                       newsJSONObject.getString("author"),
                       newsJSONObject.getString("url"),
                       newsJSONObject.getString("urlToImage")
                   )
                   newsArray.add(news)
               }
                madapter.updateNews(newsArray)
            },
            {

            }
        )
        mysingletonclass.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {

    }
}