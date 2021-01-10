package by.zharikov.shared.domain.Cocktail

import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.di.components.DaggerDaggerCocktailsComponent
import by.zharikov.shared.repository.coctails.CocktailRepository
import javax.inject.Inject

class LoadCocktailListUseCase {
    @Inject
    lateinit var cocktailRepository: CocktailRepository

    init {
        DaggerDaggerCocktailsComponent
            .create()
            .initializeLoadCocktailListUseCase(this)
    }

    suspend fun doWork(params: Params): List<Cocktail>? {
        return cocktailRepository.getCocktails(params.firstLetter)
    }


}

data class Params(val firstLetter: String)