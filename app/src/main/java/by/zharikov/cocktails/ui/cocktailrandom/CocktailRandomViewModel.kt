package by.zharikov.cocktails.ui.cocktailrandom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.domain.Cocktail.LoadCocktailRandomUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CocktailRandomViewModel : ViewModel() {

    init {
        val loadCocktailRandomUseCase = LoadCocktailRandomUseCase()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cocktailRandom = loadCocktailRandomUseCase.doWork()
                _cocktailRandom.postValue(cocktailRandom)
            } catch (e : Exception){
                _errorBus.postValue(e.message)
            }
        }
    }

    private val _cocktailRandom = MutableLiveData<List<Cocktail>>()
    val cocktailRandom: LiveData<List<Cocktail>>
        get() = _cocktailRandom

    private val _errorBus = MutableLiveData<String>()
    val errorBus:LiveData<String>
    get() = _errorBus
}