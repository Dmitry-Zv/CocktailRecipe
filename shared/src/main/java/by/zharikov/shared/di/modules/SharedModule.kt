package by.zharikov.shared.di.modules

import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.network.cocktailapi.CocktailAPI
import by.zharikov.shared.network.cocktailapi.CocktailByNameDrinkAPI
import by.zharikov.shared.network.cocktailapi.RetrofitFactory
import by.zharikov.shared.network.cocktailapi.RetrofitFactoryCocktailByNameDrink
import dagger.Module
import dagger.Provides

@Module
class SharedModule {
    @Provides
    fun providesApi():CocktailAPI{
        return RetrofitFactory().getAPI()
    }
    @Provides
    fun providesCocktailByDrinkNameApi():CocktailByNameDrinkAPI{
        return RetrofitFactoryCocktailByNameDrink().getAPI()
    }
}