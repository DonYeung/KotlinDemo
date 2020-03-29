package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.lesson.entity.Lesson

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var list = ArrayList<Lesson>()
    fun updateAndNotify(list: List<Lesson>?) {
        this.list = list as ArrayList<Lesson>
        notifyDataSetChanged()

    }

    /**
     * 静态内部类
     */
   class LessonViewHolder : BaseViewHolder  {
        constructor(itemView: View) : super(itemView)

        companion object{
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_lesson, parent, false))

            }

        }

        fun onBind(lesson: Lesson) {
            var date = lesson.date ?: "日期待定"
            setText(R.id.tv_date, date)
            setText(R.id.tv_content, lesson.content)

            val state = lesson.state
            setText(R.id.tv_state, state?.name)
            var colorRes = R.color.playback

            when (state) {
                Lesson.State.PLAYBACK -> {
                    // 即使在 {} 中也是需要 break 的。
                    colorRes = R.color.playback
                }
                Lesson.State.LIVE -> colorRes = R.color.live
                Lesson.State.WAIT -> colorRes = R.color.wait
            }

            val backgroundColor = itemView.context.getColor(colorRes)
            getView<TextView>(R.id.tv_state)?.setBackgroundColor(backgroundColor)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list.get(position))
    }
}