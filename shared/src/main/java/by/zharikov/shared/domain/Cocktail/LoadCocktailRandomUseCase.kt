package by.zharikov.shared.domain.Cocktail

import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.di.components.DaggerDaggerCocktailsComponent
import by.zharikov.shared.repository.coctails.CocktailRepository
import javax.inject.Inject

class LoadCocktailRandomUseCase {
    @Inject
   lateinit var cocktailRepository: CocktailRepository
    init {
        DaggerDaggerCocktailsComponent.create()
            .initializeLoadCocktailRandomUseCase(this)
    }

    suspend fun doWork(): List<Cocktail>? {
        return cocktailRepository.getCocktailRandom()
    }


}