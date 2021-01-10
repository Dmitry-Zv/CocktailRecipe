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
import com.squareup.picasso.Picasso
import java.lang.NullPointerException

class CocktailByFirstLetterFragment : Fragment(), ClickOnCardViewRecipe {

    private var _binding: FragmentByFirstLetterBinding? = null
    private val binding: FragmentByFirstLetterBinding
        get() = _binding!!
    private lateinit var cocktailByFirstLetterViewModel: CocktailByFirstLetterViewModel
    private lateinit var cocktailByFirstLetterFactory: CocktailByFirstLetterFactory
    private lateinit var text: String
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
        try {

            binding.materialEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    text = binding.materialEditText.text.toString()
                    cocktailByFirstLetterFactory = CocktailByFirstLetterFactory(text)
                    cocktailByFirstLetterViewModel = ViewModelProvider(
                        this@CocktailByFirstLetterFragment,
                        cocktailByFirstLetterFactory
                    ).get(CocktailByFirstLetterViewModel::class.java)
                    cocktailByFirstLetterViewModel.cocktailListFirstLetter.observe(
                        viewLifecycleOwner,
                        Observer {
                            binding.cocktailRecycler.layoutManager = LinearLayoutManager(context)
                            binding.cocktailRecycler.adapter =
                                CocktailAdapter(it, this@CocktailByFirstLetterFragment)
                        })

                    cocktailByFirstLetterViewModel.errorBus.observe(viewLifecycleOwner, Observer {
                        showAlert(
                            title = R.string.error,
                            message = it
                        )
                    })

                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
        } catch (e: NullPointerException) {
            showAlert(
                title = R.string.error,
                message = e.message.toString()
            )
        }

    }

    override fun viewNextFragment(cocktail: Cocktail) {
        model.select(cocktail)
        findNavController().navigate(R.id.action_navigation_fragment_by_first_letter_to_cocktailRecipeFragment)
    }
}
