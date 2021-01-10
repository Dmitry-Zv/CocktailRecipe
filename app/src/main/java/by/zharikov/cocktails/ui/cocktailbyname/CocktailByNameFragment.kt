package by.zharikov.cocktails.ui.cocktailbyname

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.zharikov.cocktails.R
import by.zharikov.cocktails.databinding.FragmentByNameCocktailBinding
import by.zharikov.cocktails.ui.cocktailbyfirstletter.ClickOnCardViewRecipe
import by.zharikov.cocktails.ui.cocktailbyfirstletter.CocktailAdapter
import by.zharikov.cocktails.ui.cocktailbyfirstletter.SharedViewModelByFirstLetter
import by.zharikov.shared.data.entity.cocktail.Cocktail

class CocktailByNameFragment : Fragment(), ClickOnCardViewRecipe {

    private var _binding: FragmentByNameCocktailBinding? = null
    private val binding: FragmentByNameCocktailBinding
        get() = _binding!!
    private lateinit var cocktailByNameViewModel: CocktailByNameViewModel
    private lateinit var cocktailByNameFactory: CocktailByNameFactory
    private lateinit var drinkName: String
    private val model: SharedViewModelByFirstLetter by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentByNameCocktailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.materialEditTextCocktailByName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                drinkName = binding.materialEditTextCocktailByName.text.toString()
                cocktailByNameFactory = CocktailByNameFactory(drinkName)
                cocktailByNameViewModel =
                    ViewModelProvider(this@CocktailByNameFragment, cocktailByNameFactory)
                        .get(CocktailByNameViewModel::class.java)
                cocktailByNameViewModel.cocktailByDrinkName.observe(viewLifecycleOwner, Observer {
                    binding.cocktailByNameRecycler.layoutManager = LinearLayoutManager(context)
                    binding.cocktailByNameRecycler.adapter =
                        CocktailAdapter(it, this@CocktailByNameFragment)
                })
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    }

    override fun viewNextFragment(cocktail: Cocktail) {
        model.select(cocktail)
        findNavController().navigate(R.id.action_cocktailByNameFragment_to_cocktailRecipeFragment)
    }
}