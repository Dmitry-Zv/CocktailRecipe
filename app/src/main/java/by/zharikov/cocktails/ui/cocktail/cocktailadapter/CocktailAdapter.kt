package by.zharikov.cocktails.ui.cocktailbyfirstletter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import by.zharikov.cocktails.databinding.ItemBinding
import by.zharikov.shared.data.entity.cocktail.Cocktail
import com.squareup.picasso.Picasso
import java.util.ArrayList

class CocktailAdapter(
    private var cocktailList: List<Cocktail>,
    val clickOnCardViewRecipe: ClickOnCardViewRecipe
) :
    RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder>() {
    inner class CocktailViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cocktail: Cocktail) {
            with(binding) {
                drinkName.text = cocktail.drinkName
                Picasso.get().load(cocktail.imageUrl).into(imageDrink)
                binding.root.setOnClickListener {
                    clickOnCardViewRecipe.viewNextFragment(cocktail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = ItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.bind(cocktailList[position])
    }

    override fun getItemCount(): Int {
        return cocktailList.size
    }

    fun clear() {
        cocktailList = emptyList()
        notifyDataSetChanged()
    }
}

interface ClickOnCardViewRecipe {
    fun viewNextFragment(cocktail: Cocktail)
}