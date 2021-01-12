package by.zharikov.cocktails.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.zharikov.cocktails.R
import by.zharikov.cocktails.databinding.FragmentSaveDataBinding
import by.zharikov.cocktails.ui.cocktailbyfirstletter.ClickOnCardViewRecipe
import by.zharikov.cocktails.ui.cocktailbyfirstletter.CocktailAdapter
import by.zharikov.cocktails.ui.sharedviewmodel.SharedViewModel
import by.zharikov.shared.data.entity.cocktail.Cocktail

class SaveDataFragment : Fragment(), ClickOnCardViewRecipe {

    private var _binding: FragmentSaveDataBinding? = null
    private val binding: FragmentSaveDataBinding
        get() = _binding!!

    private lateinit var saveDataViewModel: SaveDataViewModel

    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaveDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveDataViewModel = ViewModelProvider(this).get(SaveDataViewModel::class.java)
        saveDataViewModel.savedCocktail.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.textSave.text = getString(R.string.there_are_no_saved_cocktails)
            } else {
                binding.textSave.text = ""
                binding.recyclerSaveData.layoutManager = LinearLayoutManager(context)
                binding.recyclerSaveData.adapter = CocktailAdapter(it, this)
            }
        })
    }

    override fun viewNextFragment(cocktail: Cocktail) {
        model.select(cocktail)
        findNavController().navigate(R.id.action_navigation_saved_data_to_cocktailRecipeFragment)
    }
}