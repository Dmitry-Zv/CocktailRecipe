package by.zharikov.shared.network.cocktailapi

import by.zharikov.shared.data.dto.cocktail.CocktailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailAPI {
    @GET("search.php?")
   suspend fun getCocktailsByFirstLetter(
        @Query("f")
        firstLetter: String
    ):Response<CocktailResponse>
}