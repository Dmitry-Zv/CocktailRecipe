package by.zharikov.cocktails.ui.saved

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.zharikov.shared.data.db.cocktails.database.DataBaseProvider
import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.domain.Cocktail.LoadCocktailFromDataBaseListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveDataViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = DataBaseProvider.provide(app.applicationContext).getDao()

    init {
        val loadCocktailFromDataBaseListUseCase = LoadCocktailFromDataBaseListUseCase()
        CoroutineScope(Dispatchers.IO).launch {
            _savedCocktail.postValue(
                loadCocktailFromDataBaseListUseCase.doWork(
                    LoadCocktailFromDataBaseListUseCase.Params(dao)
                )
            )
        }

    }

    private val _savedCocktail = MutableLiveData<List<Cocktail>>()
    val savedCocktail: LiveData<List<Cocktail>>
        get() = _savedCocktail
}