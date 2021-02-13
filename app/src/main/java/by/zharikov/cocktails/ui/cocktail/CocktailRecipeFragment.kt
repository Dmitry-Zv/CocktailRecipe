package by.zharikov.cocktails.ui.cocktail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.zharikov.cocktails.R
import by.zharikov.cocktails.databinding.FragmentRecipeBinding
import by.zharikov.cocktails.ui.sharedviewmodel.SharedViewModel
import com.squareup.picasso.Picasso
import java.util.stream.Collectors

class CocktailRecipeFragment : Fragment() {
    private var _binding: FragmentRecipeBinding? = null
    private val binding: FragmentRecipeBinding
        get() = _binding!!
    private val model: SharedViewModel by activityViewModels()
    private lateinit var cocktailRecipeViewModel: CocktailRecipeViewModel
    private val ingredient = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cocktailRecipeViewModel =
            ViewModelProvider(this).get(CocktailRecipeViewModel::class.java)
        model.selected.observe(viewLifecycleOwner, Observer {

            with(binding) {
                drinkTextName.text = it.drinkName
                textCategory.text = it.drinkCategory
                textAlcoholic.text = it.isAlcoholic

                it.ingredientList.forEach {
                    if (it != null) {
                        if (it.isNotEmpty()) {
                            ingredient.add(it)
                        }
                    }
                }
                ingredientList.text = ingredient.stream().collect(Collectors.joining(",\n"))
                instruction.text = it.instruction
                Picasso.get().load(it.imageUrl).into(imageRecipe)
                cocktailRecipeViewModel.getCocktail(it)
                cocktailRecipeViewModel.isPresentCocktailInDB(it)
            }
        })
        cocktailRecipeViewModel.isCocktailIsSaved.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.buttonSave.visibility = View.GONE
                binding.buttonDelete.visibility = View.VISIBLE
                binding.buttonDelete.setOnClickListener {
                    cocktailRecipeViewModel.cocktailDBEntity.observe(viewLifecycleOwner, Observer {
                        cocktailRecipeViewModel.deleteCocktailFromDB(it)
                        Toast.makeText(context, getString(R.string.deleted), Toast.LENGTH_SHORT)
                            .show()
                    })
                    binding.buttonDelete.visibility = View.GONE
                    binding.buttonSave.visibility = View.VISIBLE
                    cocktailRecipeViewModel.cocktailSave(false)
                }

            } else {
                binding.buttonDelete.visibility = View.GONE
                binding.buttonSave.visibility = View.VISIBLE
                binding.buttonSave.setOnClickListener {
                    cocktailRecipeViewModel.cocktailDBEntity.observe(viewLifecycleOwner, Observer {
                        cocktailRecipeViewModel.saveCocktailToDB(it)
                        Toast.makeText(context, getString(R.string.saved), Toast.LENGTH_SHORT)
                            .show()
                    })
                    binding.buttonSave.visibility = View.GONE
                    binding.buttonDelete.visibility = View.VISIBLE
                    cocktailRecipeViewModel.cocktailSave(true)
                }
            }
        })
    }
}