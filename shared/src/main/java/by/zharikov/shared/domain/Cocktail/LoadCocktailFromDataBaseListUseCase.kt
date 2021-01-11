package by.zharikov.shared.domain.Cocktail

import by.zharikov.shared.data.db.cocktails.dao.CocktailDao
import by.zharikov.shared.di.components.DaggerDaggerCocktailsComponent
import by.zharikov.shared.repository.coctails.CocktailRepository
import javax.inject.Inject

class LoadCocktailFromDataBaseListUseCase {

    @Inject
    lateinit var cocktailRepository: CocktailRepository

    init {
        DaggerDaggerCocktailsComponent.create()
            .initializeLoadCocktailFromDataBaseListUseCase(this)
    }

    suspend fun doWork(params: Params) =
        cocktailRepository.getCocktailDb(params.dao)


    data class Params(val dao: CocktailDao)
}