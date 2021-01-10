package by.zharikov.cocktails.ui.cocktailbyname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.domain.Cocktail.LoadCocktailByDrinkNameListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CocktailByNameViewModel(private val drinkName: String) : ViewModel() {

    init {
        val loadCocktailByDrinkNameListUseCase = LoadCocktailByDrinkNameListUseCase()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cocktailListByDrinkNameUseCase = loadCocktailByDrinkNameListUseCase.doWork(
                    LoadCocktailByDrinkNameListUseCase.Params(drinkName)
                )
                _cocktailByDrinkName.postValue(cocktailListByDrinkNameUseCase)
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }

        }
    }

    private val _cocktailByDrinkName = MutableLiveData<List<Cocktail>>()
    val cocktailByDrinkName: LiveData<List<Cocktail>>
        get() = _cocktailByDrinkName

    private val _errorBus = MutableLiveData<String>()
    val errorBus: LiveData<String>
        get() = _errorBus

}