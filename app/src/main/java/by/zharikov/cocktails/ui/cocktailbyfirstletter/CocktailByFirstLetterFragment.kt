package by.zharikov.cocktails.ui.cocktailbyfirstletter

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.zharikov.cocktails.R
import by.zharikov.cocktails.databinding.FragmentByFirstLetterBinding
import by.zharikov.cocktails.ui.utils.showAlert
import by.zharikov.shared.data.entity.cocktail.Cocktail

class CocktailByFirstLetterFragment : Fragment(), ClickOnCardViewRecipe {

    private var _binding: FragmentByFirstLetterBinding? = null
    private val binding: FragmentByFirstLetterBinding
        get() = _binding!!
    private lateinit var cocktailByFirstLetterViewModel: CocktailByFirstLetterViewModel
    private lateinit var text: String

    private lateinit var cocktailAdapter: CocktailAdapter

    private val model: SharedViewModelByFirstLetter by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentByFirstLetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.materialEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                text = s.toString()
                cocktailByFirstLetterViewModel = ViewModelProvider(
                    this@CocktailByFirstLetterFragment
                ).get(CocktailByFirstLetterViewModel::class.java)
                    cocktailByFirstLetterViewModel.fetch(text)
                    cocktailByFirstLetterViewModel.cocktailListFirstLetter.observe(
                        viewLifecycleOwner,
                        Observer {
                            it?.let {
                                binding.cocktailRecycler.layoutManager =
                                    LinearLayoutManager(context)
                                cocktailAdapter =
                                    CocktailAdapter(it, this@CocktailByFirstLetterFragment)
                                binding.cocktailRecycler.adapter = cocktailAdapter

                            } ?: run {
                                showAlert(
                                    title = R.string.error,
                                    message = "Cocktails has not been found!"
                                )
                            }
                        })

                    cocktailByFirstLetterViewModel.errorBus.observe(viewLifecycleOwner, Observer {
                        showAlert(
                            title = R.string.error,
                            message = it
                        )
                    })
                    cocktailByFirstLetterViewModel.isTextSearchIsEmpty.observe(
                        viewLifecycleOwner,
                        Observer {
                            if (it) {
                                cocktailAdapter.clear()
                            }
                        })
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }

    override fun viewNextFragment(cocktail: Cocktail) {
        model.select(cocktail)
        findNavController().navigate(R.id.action_navigation_fragment_by_first_letter_to_cocktailRecipeFragment)
    }
}
