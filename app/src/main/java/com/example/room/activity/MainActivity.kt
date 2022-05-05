package com.example.room.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.room.adapter.CategoryAdapter
import com.example.room.adapter.NewsAdapter
import com.example.room.database.AppDatabase
import com.example.room.databinding.ActivityMainBinding
import com.example.room.databinding.DialogBinding
import com.example.room.entity.Category
import com.example.room.entity.News

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var list: ArrayList<News>
    private lateinit var categoryList: ArrayList<Category>
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

        list =  ArrayList()
        list.addAll(appDatabase.newsDao().getAllNews())

        val category1 = Category("Global")
        val category2 = Category("Local")
        val category3 = Category("Technology")
        appDatabase.categoryDao().insertAllCategory(category1, category2, category3)

        categoryList = ArrayList()
        categoryList.addAll(appDatabase.categoryDao().getAllCategories())

        initViews()
    }

    private fun initViews() {
        refreshAdapter()

        categoryAdapter = CategoryAdapter(categoryList)
        binding.spinner.adapter = categoryAdapter

        binding.btnAdd.setOnClickListener {
            saveNews()
        }
    }

    private fun refreshAdapter(){
        newsAdapter = NewsAdapter(list, object : NewsAdapter.OnItemClickListener{
            override fun onItemDelete(news: News, position: Int) {
                appDatabase.newsDao().deleteNews(news)
                list.remove(news)
                newsAdapter.notifyItemRemoved(position)
                newsAdapter.notifyItemRangeChanged(position, list.size)
                Toast.makeText(this@MainActivity, "Successfully deleted", Toast.LENGTH_SHORT).show()
            }

            override fun onItemEdit(news: News, position: Int) {
                val dialog = AlertDialog.Builder(this@MainActivity)
                val dialogBinding = DialogBinding.inflate(layoutInflater)
                dialog.setView(dialogBinding.root)

                dialogBinding.etTitle.setText(news.title)
                dialogBinding.etDescriptiom.setText(news.desc)

                dialog.setPositiveButton("Ok"
                ) { dialog, which ->
                    val title = dialogBinding.etTitle.text.toString().trim()
                    val desc = dialogBinding.etDescriptiom.text.toString().trim()

                    news.title = title
                    news.desc = desc

                    appDatabase.newsDao().updateNews(news)
                    newsAdapter.notifyItemChanged(position)
                    Toast.makeText(this@MainActivity, "Successfully edited", Toast.LENGTH_SHORT).show()
                }
                dialog.show()
            }
        })

        binding.rvMain.adapter = newsAdapter
    }

    private fun saveNews() {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescriptiom.text.toString().trim()

        val news = News()
        news.title = title
        news.desc = description
        val selectedItemPosition = binding.spinner.selectedItemPosition
        val category = categoryList[selectedItemPosition]
        news.categoryId = category.id
        appDatabase.newsDao().addNews(news)
        val id = appDatabase.newsDao().getNewsByID(title, description)
        news.id = id
        list.add(news)
        newsAdapter.notifyItemInserted(list.size)
        clearEditTexts()
    }

    private fun clearEditTexts(){
        binding.etTitle.setText("")
        binding.etDescriptiom.setText("")
    }

}