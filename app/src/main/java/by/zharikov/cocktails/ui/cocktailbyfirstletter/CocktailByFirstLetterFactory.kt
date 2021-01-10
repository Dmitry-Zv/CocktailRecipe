package by.zharikov.cocktails.ui.cocktailbyfirstletter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CocktailByFirstLetterFactory(private val text:String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailByFirstLetterViewModel::class.java)){

            return CocktailByFirstLetterViewModel(text) as T
        }
        throw IllegalArgumentException("Wrong Dependencies")
    }


}