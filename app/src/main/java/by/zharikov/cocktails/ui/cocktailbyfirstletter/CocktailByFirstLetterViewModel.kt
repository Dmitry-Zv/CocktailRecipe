package by.zharikov.cocktails.ui.cocktailbyfirstletter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.domain.Cocktail.LoadCocktailListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CocktailByFirstLetterViewModel() : ViewModel() {
    fun fetch(text: String) {
        if (text.isNotEmpty()) {
            _isTextSearchIsEmpty.postValue(false)
            val loadCocktailListUseCase = LoadCocktailListUseCase()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val cocktailsListOnFirstLetterUseCase = loadCocktailListUseCase.doWork(
                        LoadCocktailListUseCase.Params(text)
                    )
                    _cocktailListFirstLetter.postValue(cocktailsListOnFirstLetterUseCase)
                } catch (e: Exception) {
                    _errorBus.postValue(e.message)
                }
            }
        } else {
            _isTextSearchIsEmpty.value = true
        }
    }

    private val _cocktailListFirstLetter = MutableLiveData<List<Cocktail>>()
    val cocktailListFirstLetter: LiveData<List<Cocktail>>
        get() = _cocktailListFirstLetter

    private val _errorBus = MutableLiveData<String>()
    val errorBus: LiveData<String>
        get() = _errorBus

    private val _isTextSearchIsEmpty = MutableLiveData<Boolean>()
    val isTextSearchIsEmpty: LiveData<Boolean>
        get() = _isTextSearchIsEmpty

}