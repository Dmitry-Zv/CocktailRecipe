package by.zharikov.cocktails.ui.cocktailrandom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.zharikov.cocktails.R
import by.zharikov.cocktails.databinding.FragmentRecipeBinding
import by.zharikov.cocktails.ui.utils.showAlert
import com.squareup.picasso.Picasso

class CocktailRandomFragment : Fragment() {

    private var _binding : FragmentRecipeBinding? = null
    private val binding : FragmentRecipeBinding
    get() = _binding!!
    private lateinit var cocktailRandomViewModel: CocktailRandomViewModel

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
        cocktailRandomViewModel = ViewModelProvider(this).get(CocktailRandomViewModel::class.java)
        cocktailRandomViewModel.cocktailRandom.observe(viewLifecycleOwner, Observer {
            with(binding) {
                drinkTextName.text = it[0].drinkName
                textCategory.text = it[0].drinkCategory
                textAlcoholic.text = it[0].isAlcoholic
                ingredientList.text = it[0].ingredientList.toString()
                instruction.text = it[0].instruction
                Picasso.get().load(it[0].imageUrl).into(imageRecipe)
            }
        })
        cocktailRandomViewModel.errorBus.observe(viewLifecycleOwner, Observer {
            showAlert(
                title = R.string.error,
                message = it
            )
        })
    }
}