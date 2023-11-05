
import com.casibua.animal.midterm.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(private val animals: List<String>, private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {
    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animalName: TextView = itemView.findViewById(R.id.animalName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_animal, parent, false)
        return AnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]
        holder.animalName.text = animal

        // Set an OnClickListener to open the AnimalDetailsActivity
        holder.itemView.setOnClickListener {
            onItemClick(animal)
        }
    }

    override fun getItemCount(): Int {
        return animals.size
    }
}
