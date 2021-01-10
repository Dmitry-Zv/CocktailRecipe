package by.zharikov.shared.network.cocktailapi

import by.zharikov.shared.data.dto.cocktail.CocktailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface CocktailByNameDrinkAPI  {
    @GET("search.php?")
    suspend fun getCocktailByNameDrinkAPI(
        @Query("s")
        cocktailName:String
    ):Response<CocktailResponse>
}