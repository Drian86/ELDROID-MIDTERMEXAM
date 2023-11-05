
package com.casibua.animal.midterm

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnimalDetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ANIMAL_NAME = "animal_name"
        const val EXTRA_ANIMAL_DESCRIPTION = "animal_description"
    }

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var animalName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_details)

        sharedPreferences = getSharedPreferences("BlockedAnimals", Context.MODE_PRIVATE)
        animalName = intent.getStringExtra(EXTRA_ANIMAL_NAME) ?: ""
        val animalDescription = intent.getStringExtra(EXTRA_ANIMAL_DESCRIPTION) ?: ""

        val animalNameTextView: TextView = findViewById(R.id.animalNameTextView)
        val animalDescriptionTextView: TextView = findViewById(R.id.animalDescriptionTextView)
        val blockButton: Button = findViewById(R.id.blockButton)

        animalNameTextView.text = animalName
        animalDescriptionTextView.text = animalDescription

        blockButton.setOnClickListener {
            blockAnimal()
        }
    }

    private fun blockAnimal() {
        val blockedAnimalsSet = sharedPreferences.getStringSet("blocked_animals", mutableSetOf()) ?: mutableSetOf()
        blockedAnimalsSet.add(animalName)
        sharedPreferences.edit()
            .putStringSet("blocked_animals", blockedAnimalsSet)
            .apply()
        finish()
    }
}
