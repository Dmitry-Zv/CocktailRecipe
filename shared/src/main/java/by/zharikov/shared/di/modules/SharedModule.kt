package by.zharikov.shared.di.modules

import by.zharikov.shared.network.cocktailapi.*
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
    @Provides
    fun providesCocktailRandomAPI():CocktailRandomAPI{
        return RetrofitFactoryCocktailRandom().getApi()
    }
}