package by.zharikov.cocktails.ui.cocktailbyfirstletter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.domain.Cocktail.LoadCocktailListUseCase
import by.zharikov.shared.domain.Cocktail.Params
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CocktailByFirstLetterViewModel(private val text: String) : ViewModel() {

    init {
        val loadCocktailListUseCase = LoadCocktailListUseCase()
        CoroutineScope(Dispatchers.IO).launch {
            try {
               val cocktailsListOnFirstLetterUseCase =  loadCocktailListUseCase.doWork(Params(text))
                _cocktailListFirstLetter.postValue(cocktailsListOnFirstLetterUseCase)
            } catch (e:Exception){
                _errorBus.postValue(e.message)
            }
        }
    }

    private val _cocktailListFirstLetter = MutableLiveData<List<Cocktail>>()
    val cocktailListFirstLetter:LiveData<List<Cocktail>>
    get() = _cocktailListFirstLetter

    private val _errorBus = MutableLiveData<String>()
    val errorBus:LiveData<String>
    get() = _errorBus

}