
package com.casibua.animal.midterm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BlockedAnimalAdapter(private val blockedAnimals: List<String>, private val onUnblockClick: (String) -> Unit) : RecyclerView.Adapter<BlockedAnimalAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val blockedAnimalName: TextView = itemView.findViewById(R.id.blockedAnimalName)
        val unblockButton: Button = itemView.findViewById(R.id.unblockButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_blocked_animal, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blockedAnimal = blockedAnimals[position]
        holder.blockedAnimalName.text = blockedAnimal

        holder.unblockButton.setOnClickListener {
            onUnblockClick(blockedAnimal)
        }
    }

    override fun getItemCount(): Int {
        return blockedAnimals.size
    }
}
