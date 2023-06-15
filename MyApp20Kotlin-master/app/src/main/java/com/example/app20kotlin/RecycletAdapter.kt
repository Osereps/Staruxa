package com.example.app20kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app20kotlin.sampledata.Zametki

class RecycletAdapter: RecyclerView.Adapter<RecycletAdapter.ViewHolder>{
    private val recyclerViewInterface: RecyclerViewInterface?
    private val context: Context?
    private val groupsList: ArrayList<Zametki>?

    constructor(
        recyclerViewInterface: RecyclerViewInterface?,
        context: Context?,
        groupsList: ArrayList<Zametki>?
    ) : super() {
        this.recyclerViewInterface = recyclerViewInterface
        this.context = context
        this.groupsList = groupsList
    }


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_1_card, parent, false)
        return ViewHolder(view, recyclerViewInterface)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val group = groupsList!![position]
        //        holder.id.setText(group.getID());
//        holder.key.setText(group.getUsers_Key());
        holder.text.text = group.getZametki_Text()
    }

     override fun getItemCount(): Int {
        return groupsList!!.size
    }

    class ViewHolder internal constructor(
        view: View,
        private val recyclerViewInterface: RecyclerViewInterface?
    ) :
        RecyclerView.ViewHolder(view) {
        var id: TextView? = null
        var key: TextView? = null
        var text: TextView

        init {
            text = view.findViewById(R.id.text)
            itemView.setOnClickListener {
                if (this.recyclerViewInterface != null) {
                    val pos = adapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        this.recyclerViewInterface.onItemClick(pos)
                    }
                }
            }
        }
    }
}