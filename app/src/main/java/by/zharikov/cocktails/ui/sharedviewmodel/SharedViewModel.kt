package by.zharikov.cocktails.ui.sharedviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.zharikov.shared.data.entity.cocktail.Cocktail

class SharedViewModel : ViewModel() {
    private val _selected = MutableLiveData<Cocktail>()
    val selected: LiveData<Cocktail>
        get() = _selected

    fun select(cocktail: Cocktail) {
        _selected.value = cocktail
    }
}