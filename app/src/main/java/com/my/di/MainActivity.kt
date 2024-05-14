package com.my.di

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.di.adapter.MainAdapter
import com.my.di.databinding.ActivityMainBinding
import com.my.di.model.NewsData
import com.my.di.utils.AppConstant
import com.my.di.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm : MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private lateinit var manager: LinearLayoutManager

    private var articleList: ArrayList<NewsData> = arrayListOf()
    private var pageNumber: Int = 1
    private var totalItems: Int = 0
    private var scrollOutItems: Int = 0
    private var isScrolling: Boolean = false
    private var currentItems = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        setupUI()
        setUpEvents()

        setContentView(binding.root)

    }

    private fun setupUI() {
        manager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.layoutManager = manager
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun setUpEvents() {
        vm.getNews(pageNumber,"business")

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = manager.childCount
                totalItems = manager.itemCount
                scrollOutItems = manager.findFirstVisibleItemPosition()
                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false
                    if (articleList.size % AppConstant.PAGE_SIZE == 0) {
                        pageNumber += 1
                        vm.getNews(pageNumber,"business")
                    }
                }
            }
        })

        vm.news.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
//                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
//                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
//                    progressBar.visibility = View.VISIBLE
//                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
//                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(data: List<NewsData>) {
        articleList.addAll(data)
        adapter.addData(data)

        adapter.notifyDataSetChanged()
    }
}