package com.thomaslandais.m1info.ex4

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast





class AdapterTask(private val context: Context): BaseAdapter() {
    class Task(val name: String, val priority: String)

    private val tasks = ArrayList<Task>()
    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(p0: Int): Task {
        return tasks[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View? {
        var myView: View? = convertView
        if (myView == null) {
            myView = LayoutInflater.from(context).inflate(R.layout.ligne, viewGroup, false)
        }

        val taskName = myView!!.findViewById<TextView>(R.id.tv_taskName)
        val priority = myView.findViewById<View>(R.id.view_priorityColor)

        taskName.text = getItem(position).name

        when (getItem(position).priority.toInt()) {
            1 -> priority.setBackgroundColor(Color.GREEN)
            2 -> priority.setBackgroundColor(Color.YELLOW)
            3 -> priority.setBackgroundColor(Color.RED)
        }

        // add a click listener to the task name
        taskName.setOnClickListener {
            Toast.makeText(context, "Task ${getItem(position).name} ; Priorité : ${getItem(position).priority} ", Toast.LENGTH_SHORT).show()
        }

        return myView
    }

    fun addTask(name: String, priority: String) {
        tasks.add(Task(name, priority))
        notifyDataSetChanged()
    }

}