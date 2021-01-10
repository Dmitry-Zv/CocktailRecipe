package by.zharikov.shared.domain.Cocktail

import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.di.components.DaggerDaggerCocktailsComponent
import by.zharikov.shared.repository.coctails.CocktailRepository
import javax.inject.Inject

class LoadCocktailByDrinkNameListUseCase {
    @Inject
  lateinit var cocktailRepository:CocktailRepository
  init {
      DaggerDaggerCocktailsComponent
          .create()
          .initializeLoadCocktailByDrinkNameListUseCase(this)
  }

    suspend fun doWork(params: Params):List<Cocktail>?{
       return cocktailRepository.getCocktails(params.drinkName)
    }

    data class Params(val drinkName:String)
}
