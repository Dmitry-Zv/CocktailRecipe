package by.zharikov.shared.repository.coctails

import by.zharikov.shared.data.dto.cocktail.CocktailResponse
import by.zharikov.shared.network.cocktailapi.CocktailAPI
import by.zharikov.shared.network.cocktailapi.CocktailByNameDrinkAPI
import retrofit2.Response
import javax.inject.Inject

class CocktailDataSource @Inject constructor(private val api: CocktailAPI, private val cocktailByDrinkNameAPI: CocktailByNameDrinkAPI) {

    suspend fun getApi(firstLetter: String): Response<CocktailResponse> {
        return api.getCocktailsByFirstLetter(firstLetter)
    }

    suspend fun getCocktailByDrinkNameAPI(drinkName:String):Response<CocktailResponse>{
        return cocktailByDrinkNameAPI.getCocktailByNameDrinkAPI(drinkName)
    }

}