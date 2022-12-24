package com.hari.colorit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.hari.colorit.R
import com.hari.colorit.databinding.ListItemGridBinding

class GridAdapter(private var gridItems: ArrayList<Int> = ArrayList()) :
    RecyclerView.Adapter<GridAdapter.MainViewHolder>() {

    private var firstSelectedPos: String = ""
    private var secondSelectedPos: String = ""

    fun updateGridItems(gridItems: ArrayList<Int>) {
        this.gridItems = gridItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        return MainViewHolder(
            ListItemGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.item.setBackgroundColor(
            ResourcesCompat.getColor(
                holder.itemView.context.resources,
                gridItems[position], holder.itemView.context.theme
            )
        )
        holder.item.setImageDrawable(null)
        holder.item.setOnClickListener {


            if (holder.item.drawable != null) {
                holder.item.setImageDrawable(null)
                if (firstSelectedPos.isNotEmpty() && holder.adapterPosition == firstSelectedPos.toInt()) {
                    firstSelectedPos = ""
                } else if (secondSelectedPos.isNotEmpty() && holder.adapterPosition == secondSelectedPos.toInt()) {
                    secondSelectedPos = ""
                }
            } else {

                if (firstSelectedPos.isEmpty() || secondSelectedPos.isEmpty()) {

                    holder.item.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            holder.itemView.context.resources, R.drawable.tick,
                            holder.itemView.context.theme
                        )
                    )
                    if (firstSelectedPos.isEmpty()) {
                        firstSelectedPos = holder.adapterPosition.toString()

                        if (secondSelectedPos.isNotEmpty()) {
                            swapColors()
                        }
                    } else {

                        secondSelectedPos = holder.adapterPosition.toString()
                        if (firstSelectedPos.isNotEmpty()) {
                            swapColors()
                        }
                    }
                }
            }
        }
    }

    private fun swapColors() {
        val firstColor = gridItems[firstSelectedPos.toInt()]
        val secondColor = gridItems[secondSelectedPos.toInt()]
        gridItems[firstSelectedPos.toInt()] = secondColor
        gridItems[secondSelectedPos.toInt()] = firstColor
        notifyItemChanged(firstSelectedPos.toInt())
        notifyItemChanged(secondSelectedPos.toInt())
        firstSelectedPos = ""
        secondSelectedPos = ""
    }

    override fun getItemCount(): Int {

        return gridItems.size
    }

    class MainViewHolder(itemView: ListItemGridBinding) : RecyclerView.ViewHolder(itemView.root) {
        val item = itemView.containerListItemGrid
    }
}