
package com.casibua.animal.midterm

import AnimalAdapter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AnimalNamesActivity : AppCompatActivity() {
    private val animalList = listOf(
        "Ant", "Bear", "Cat", "Dog", "Elephant", "Fox", "Giraffe", "Horse",
        "Iguana", "Jaguar", "Kangaroo", "Lion", "Monkey", "Newt", "Octopus",
        "Panda", "Quokka", "Raccoon", "Squirrel", "Tiger", "Uakari", "Vulture",
        "Walrus", "X-ray Tetra", "Yak", "Zebra"
    )

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_names)

        sharedPreferences = getSharedPreferences("BlockedAnimals", Context.MODE_PRIVATE)

        val recyclerView: RecyclerView = findViewById(R.id.animalList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = AnimalAdapter(getFilteredAnimalList(), this::openAnimalDetails)
        recyclerView.adapter = adapter
    }

    private fun getFilteredAnimalList(): List<String> {
        val blockedAnimalsSet = sharedPreferences.getStringSet("blocked_animals", mutableSetOf()) ?: mutableSetOf()
        return animalList.filterNot { blockedAnimalsSet.contains(it) }
    }

    private fun openAnimalDetails(animalName: String) {
        val intent = Intent(this, AnimalDetailsActivity::class.java)
        intent.putExtra(AnimalDetailsActivity.EXTRA_ANIMAL_NAME, animalName)
        intent.putExtra(AnimalDetailsActivity.EXTRA_ANIMAL_DESCRIPTION, "Description goes here")
        startActivity(intent)
    }
}
