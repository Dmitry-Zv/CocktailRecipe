package by.zharikov.cocktails.ui.cocktailbyname

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CocktailByNameFactory(private val drinkName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailByNameViewModel::class.java)) {
            return CocktailByNameViewModel(drinkName) as T
        }
        throw IllegalArgumentException("Wrong Dependencies")

    }
}