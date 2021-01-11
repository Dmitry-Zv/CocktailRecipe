package by.zharikov.shared.domain.Cocktail

import by.zharikov.shared.data.db.cocktails.dao.CocktailDao
import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.di.components.DaggerDaggerCocktailsComponent
import by.zharikov.shared.repository.coctails.CocktailRepository
import javax.inject.Inject

class DeleteCocktailFromDataBaseUseCase {

    @Inject
    lateinit var cocktailRepository: CocktailRepository

    init {
        DaggerDaggerCocktailsComponent.create()
            .initializeDeleteCocktailFromDataBaseUseCase(this)
    }

    suspend fun doWork(params: Params) {
        cocktailRepository.delete(params.dao, params.cocktail)
    }

    data class Params(
        val dao: CocktailDao,
        val cocktail: Cocktail
    )
}