
package com.casibua.animal.midterm

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ManageBlockActivity : AppCompatActivity() {
    private lateinit var blockedAnimalsList: MutableList<String>
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var adapter: BlockedAnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_block)

        sharedPreferences = getSharedPreferences("BlockedAnimals", Context.MODE_PRIVATE)
        blockedAnimalsList = sharedPreferences.getStringSet("blocked_animals", emptySet())?.toMutableList() ?: mutableListOf()

        val recyclerView: RecyclerView = findViewById(R.id.blockedList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = BlockedAnimalAdapter(blockedAnimalsList) { blockedAnimal ->
            // Handle unblocking the animal
            blockedAnimalsList.remove(blockedAnimal)
            saveBlockedAnimalsToSharedPreferences()
            adapter.notifyDataSetChanged()
        }

        recyclerView.adapter = adapter
    }

    private fun saveBlockedAnimalsToSharedPreferences() {
        sharedPreferences.edit()
            .putStringSet("blocked_animals", blockedAnimalsList.toSet())
            .apply()
    }
}
