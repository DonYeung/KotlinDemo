package com.example.app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.sdk.dp.*
import com.example.app.R
import com.example.app.entity.Fruit


class FruitRecycleAdapter(val fruits: List<Fruit>, var sFragmentManager: FragmentManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mTypeList = mutableListOf<Int>()
    init {
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(0) //normal
        mTypeList.add(1) //资讯
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage = view.findViewById<ImageView>(R.id.fruit_image)
        val fruitName = view.findViewById<TextView>(R.id.fruit_text)
    }

    inner class NewsViewHolder(view: View) :RecyclerView.ViewHolder(view){
        private var mIDPWidget: IDPWidget? = null
        private lateinit var mFragmentManager: FragmentManager

        init {
//            initView(mFragmentManager)

            initNewsWidget()
        }

        fun initView(fragmentManager: FragmentManager) {
            val frameLayout = itemView.findViewById<View>(R.id.murphy_news_fl) as FrameLayout
            mFragmentManager = fragmentManager

            /* if (murphyNewsFragment == null){
            murphyNewsFragment = new MurphyNewsFragment();
        }else {
            //if (murphyNewsFragment.isAdded()){
                if (fragmentManager != null){
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.murphy_news_fl, murphyNewsFragment);
                    transaction.commitNowAllowingStateLoss();
                    Log.d("znl","initViews");
                }
            //}
        }*/if (fragmentManager != null) {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.murphy_news_fl, mIDPWidget!!.fragment)
                transaction.commitNowAllowingStateLoss()
                Log.d("znl", "initViews")
            }
        }

        fun buildNewsTabsWidget(params: DPWidgetNewsParams?): IDPWidget? {
            //创建多频道新闻组件
            return getFactory().createNewsTabs(params)
        }
        private fun getFactory(): IDPWidgetFactory {
            //一定要初始化后才能调用，否则会发生异常问题
            return DPSdk.factory()
        }


        private fun initNewsWidget() {
            mIDPWidget = buildNewsTabsWidget(DPWidgetNewsParams.obtain() // 一定要设置代码位id，否则影响收入
                    .adNewsListCodeId("945494915") // 新闻列表广告位 id
                    .adNewsFirstCodeId("945494916") // 新闻详情页首卡广告位 id
                    .adNewsSecondCodeId("945494918") // 新闻详情页底部广告位 id
                    .adVideoFirstCodeId("945494919") // 视频详情页后贴广告位 id
                    .adVideoSecondCodeId("945494920") // 视频详情页底部广告位 id
                    .adRelatedCodeId("945494921") // 相关推荐广告位 id
                    .listener(object : IDPNewsListener() {
                        override fun onDPRefreshFinish() {
                            super.onDPRefreshFinish()
                        }

                        override fun onDPNewsItemClick(p0: MutableMap<String, Any>?) {
                            super.onDPNewsItemClick(p0)
                        }

                        override fun onDPVideoPlay(p0: MutableMap<String, Any>?) {
                            super.onDPVideoPlay(p0)
                        }
                    }))
        }

    }

   /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
        if (viewType==1) {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false))
        }else{
            val inflater = LayoutInflater.from(parent.context)
            return NewsViewHolder(inflater.inflate(R.layout.murphy_news_holder_layout,parent,false));
        }
    }*/

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
 //        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false))

         if (viewType==0) {
             return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false))
         }else{
             val inflater = LayoutInflater.from(parent.context)
             return NewsViewHolder(inflater.inflate(R.layout.murphy_news_holder_layout,parent,false));
         }
     }

     override fun getItemViewType(position: Int): Int {
 //        return super.getItemViewType(position)
         return if (mTypeList.size > 0) mTypeList[position] else -1
     }
     override fun getItemCount(): Int {
         return mTypeList.size
     }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder){
            val fruit = fruits[position]
            holder.fruitImage.setImageResource(R.drawable.iv_avatar)
            holder.fruitName.text = fruit.name
        }/*else if(holder is NewsViewHolder){

        }*/
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder is NewsViewHolder){
            holder.initView(sFragmentManager)
        }
    }
}