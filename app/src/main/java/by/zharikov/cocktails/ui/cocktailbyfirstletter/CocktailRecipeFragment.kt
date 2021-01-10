package by.zharikov.cocktails.ui.cocktailbyfirstletter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import by.zharikov.cocktails.databinding.FragmentRecipeBinding
import by.zharikov.shared.data.entity.cocktail.Cocktail
import com.squareup.picasso.Picasso

class CocktailRecipeFragment : Fragment() {
    private var _binding: FragmentRecipeBinding? = null
    private val binding: FragmentRecipeBinding
        get() = _binding!!
    private val model: SharedViewModelByFirstLetter by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.selected.observe(viewLifecycleOwner, Observer {

            with(binding) {
                drinkTextName.text = it.drinkName
                textCategory.text = it.drinkCategory
                textAlcoholic.text = it.isAlcoholic
                ingredientList.text = it.ingredientList.toString()
                instruction.text = it.instruction
                Picasso.get().load(it.imageUrl).into(imageRecipe)
            }
        })
    }

}