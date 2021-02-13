package by.zharikov.cocktails.ui.cocktailrandom

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.zharikov.cocktails.R
import by.zharikov.cocktails.databinding.FragmentRandomRecipeBinding
import by.zharikov.cocktails.databinding.FragmentRecipeBinding
import by.zharikov.cocktails.ui.cocktail.CocktailRecipeViewModel
import by.zharikov.cocktails.ui.utils.showAlert
import com.squareup.picasso.Picasso
import java.util.stream.Collectors

class CocktailRandomFragment : Fragment() {

    private var _binding: FragmentRandomRecipeBinding? = null
    private val binding: FragmentRandomRecipeBinding
        get() = _binding!!
    private lateinit var cocktailRandomViewModel: CocktailRandomViewModel
    private val ingredient = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.VISIBLE
        cocktailRandomViewModel = ViewModelProvider(this).get(CocktailRandomViewModel::class.java)
        cocktailRandomViewModel.cocktailRandom.observe(viewLifecycleOwner, Observer {
            with(binding) {
                drinkTextName.text = it[0].drinkName
                textCategory.text = it[0].drinkCategory
                textAlcoholic.text = it[0].isAlcoholic
                it[0].ingredientList.forEach {
                    if (it != null) {
                        if (it.isNotEmpty()) {
                            ingredient.add(it)
                        }
                    }
                }
                ingredientList.text = ingredient.stream().collect(Collectors.joining(",\n"))
                instruction.text = it[0].instruction
                Picasso.get().load(it[0].imageUrl).into(imageRecipe)
                cocktailRandomViewModel.isPresentCocktailInDB(it[0])
                cocktailRandomViewModel.getCocktail(it[0])
                binding.constraintLayout.visibility = View.VISIBLE
            }
        })
        binding.progressBar.visibility = View.GONE

        cocktailRandomViewModel.errorBus.observe(viewLifecycleOwner, Observer {
            showAlert(
                title = R.string.error,
                message = it
            )
        })
        cocktailRandomViewModel.isCocktailIsSaved.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.buttonSave.visibility = View.GONE
                binding.buttonDelete.visibility = View.VISIBLE
                binding.buttonDelete.setOnClickListener {
                    cocktailRandomViewModel.cocktailDBEntity.observe(viewLifecycleOwner, Observer {
                        cocktailRandomViewModel.deleteCocktailFromDB(it)
                        Toast.makeText(context, getString(R.string.deleted), Toast.LENGTH_SHORT)
                            .show()
                    })
                    binding.buttonDelete.visibility = View.GONE
                    binding.buttonSave.visibility = View.VISIBLE
                    cocktailRandomViewModel.cocktailSave(false)
                }


            } else {
                binding.buttonDelete.visibility = View.GONE
                binding.buttonSave.visibility = View.VISIBLE
                binding.buttonSave.setOnClickListener {
                    cocktailRandomViewModel.cocktailDBEntity.observe(viewLifecycleOwner, Observer {
                        cocktailRandomViewModel.saveCocktailToDB(it)
                        Toast.makeText(context, getString(R.string.saved), Toast.LENGTH_SHORT)
                            .show()
                    })
                    binding.buttonSave.visibility = View.GONE
                    binding.buttonDelete.visibility = View.VISIBLE
                    cocktailRandomViewModel.cocktailSave(true)
                }
            }
        })
    }
}