package by.zharikov.shared.di.components

import by.zharikov.shared.di.modules.SharedModule
import by.zharikov.shared.domain.Cocktail.*
import dagger.Component

@Component(modules = [SharedModule::class])
interface DaggerCocktailsComponent {
    fun initializeLoadCocktailListUseCase(loadCocktailListUseCase: LoadCocktailListUseCase)

    fun initializeLoadCocktailByDrinkNameListUseCase(loadCocktailByDrinkNameListUseCase: LoadCocktailByDrinkNameListUseCase)

    fun initializeLoadCocktailRandomUseCase(loadCocktailRandomUseCase: LoadCocktailRandomUseCase)

    fun initializeLoadCocktailFromDataBaseListUseCase(loadCocktailFromDataBaseListUseCase: LoadCocktailFromDataBaseListUseCase)

    fun initializeSaveCocktailToDataBaseUseCase(saveCocktailToDataBaseUseCase: SaveCocktailToDataBaseUseCase)

    fun initializeDeleteCocktailFromDataBaseUseCase(deleteCocktailFromDataBaseUseCase: DeleteCocktailFromDataBaseUseCase)

    fun initializeCheckCocktailToCocktailDBUseCase(checkCocktailToCocktailDBUseCase:CheckCocktailWithCocktailIDBUseCase)
}