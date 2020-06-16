package com.muslim.taskmadarsoft.kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.muslim.taskmadarsoft.R
import com.muslim.taskmadarsoft.kotlin.data.Info
import kotlinx.android.synthetic.main.item_info.view.*

class InfoAdapter : ListAdapter<Info, InfoAdapter.NoteHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Info>() {
            override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean {
                return oldItem.name == newItem.name && oldItem.jobtitle == newItem.jobtitle
                        && oldItem.age == newItem.age
            }
        }
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentInfo: Info = getItem(position)

        holder.name.text = currentInfo.name
        holder.job.text = currentInfo.jobtitle
        holder.age.text = currentInfo.age.toString()
        holder.gender.text = currentInfo.gender


    }

    fun getNoteAt(position: Int): Info {
        return getItem(position)
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        var name: TextView = itemView.name
        var job: TextView = itemView.job
        var age: TextView = itemView.age
        var gender: TextView = itemView.gender


    }

    interface OnItemClickListener {
        fun onItemClick(note: Info)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
