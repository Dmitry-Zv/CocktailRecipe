package by.zharikov.shared.di.components

import by.zharikov.shared.di.modules.SharedModule
import by.zharikov.shared.domain.Cocktail.LoadCocktailByDrinkNameListUseCase
import by.zharikov.shared.domain.Cocktail.LoadCocktailListUseCase
import dagger.Component

@Component(modules = [SharedModule::class])
interface DaggerCocktailsComponent {
    fun initializeLoadCocktailListUseCase(loadCocktailListUseCase: LoadCocktailListUseCase)

    fun initializeLoadCocktailByDrinkNameListUseCase(loadCocktailByDrinkNameListUseCase: LoadCocktailByDrinkNameListUseCase)
}