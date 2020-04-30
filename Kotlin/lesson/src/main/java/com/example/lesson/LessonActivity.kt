package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.core.BaseView
import com.example.lesson.entity.Lesson

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener {
//    private var lessonPresenter = LessonPresenter(this) // 这里由于每次调用的话 可能会调用lessonPresenter(会产生新对象) or  getPresenter() 令人迷惑调用


   /* override fun getPresenter(): LessonPresenter {
        return lessonPresenter;
    }*/

  /* override val presenter: LessonPresenter
       get() =  LessonPresenter(this)*/   //如果这样子返回的话还是会调用产生对象
    //所以使用kotlin 的 特性 委托 by lazy   这样只会在第一次生产对象，之后都是调用presenter
    override val presenter: LessonPresenter by lazy {
      LessonPresenter(this)
    }

    private var lessonAdapter = LessonAdapter()

    private lateinit var refreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)


        var recyclerView = findViewById<RecyclerView>(R.id.list).run {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }

        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).run {
            setOnRefreshListener { presenter.fetchData() }
            isRefreshing = true
            return@run this
        }


        presenter.fetchData()


    }

    fun showResult(lessons: List<Lesson>?) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        presenter.showPlayback()
        return false
    }




}