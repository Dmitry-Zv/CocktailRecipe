package by.zharikov.cocktails.ui.cocktailrandom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.zharikov.shared.data.db.cocktails.database.DataBaseProvider
import by.zharikov.shared.data.db.cocktails.entity.CocktailDBEntity
import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.domain.Cocktail.CheckCocktailWithCocktailIDBUseCase
import by.zharikov.shared.domain.Cocktail.DeleteCocktailFromDataBaseUseCase
import by.zharikov.shared.domain.Cocktail.LoadCocktailRandomUseCase
import by.zharikov.shared.domain.Cocktail.SaveCocktailToDataBaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CocktailRandomViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = DataBaseProvider.provide(app.applicationContext).getDao()


    init {
        val loadCocktailRandomUseCase = LoadCocktailRandomUseCase()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cocktailRandom = loadCocktailRandomUseCase.doWork()
                _cocktailRandom.postValue(cocktailRandom)
            } catch (e: Exception) {
                _errorBus.postValue(e.message)
            }
        }
    }


    fun getCocktail(cocktail: Cocktail) {
        _cocktailDBEntity.value = cocktail
    }


    fun isPresentCocktailInDB(cocktail: Cocktail) {
        CoroutineScope(Dispatchers.IO).launch {
            val cocktailDBEntity: CocktailDBEntity? = checkCocktailWithCocktailIDBUseCase.doWork(
                CheckCocktailWithCocktailIDBUseCase.Params(
                    dao,
                    cocktail
                )
            )
            if (cocktailDBEntity != null) {
                _isCocktailIsSaved.postValue(true)
            } else {
                _isCocktailIsSaved.postValue(false)
            }


        }
    }

    fun saveCocktailToDB(cocktail: Cocktail) {
        val saveCocktailToDataBaseUseCase = SaveCocktailToDataBaseUseCase()
        CoroutineScope(Dispatchers.IO).launch {
            saveCocktailToDataBaseUseCase.doWork(
                SaveCocktailToDataBaseUseCase.Params(
                    dao,
                    cocktail
                )
            )
        }
    }

    fun deleteCocktailFromDB(cocktail: Cocktail) {
        val deleteCocktailFromDataBaseUseCase = DeleteCocktailFromDataBaseUseCase()
        CoroutineScope(Dispatchers.IO).launch {
            deleteCocktailFromDataBaseUseCase.doWork(
                DeleteCocktailFromDataBaseUseCase.Params(
                    dao,
                    cocktail
                )
            )
        }
    }


    private val _cocktailRandom = MutableLiveData<List<Cocktail>>()
    val cocktailRandom: LiveData<List<Cocktail>>
        get() = _cocktailRandom

    private val _errorBus = MutableLiveData<String>()
    val errorBus: LiveData<String>
        get() = _errorBus

    private val checkCocktailWithCocktailIDBUseCase = CheckCocktailWithCocktailIDBUseCase()

    private val _cocktailDBEntity = MutableLiveData<Cocktail>()
    val cocktailDBEntity: LiveData<Cocktail>
        get() = _cocktailDBEntity

    private val _isCocktailIsSaved = MutableLiveData<Boolean>()
    val isCocktailIsSaved: LiveData<Boolean>
        get() = _isCocktailIsSaved
}