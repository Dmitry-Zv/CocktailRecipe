package by.zharikov.shared.network.cocktailapi

import by.zharikov.shared.data.dto.cocktail.CocktailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailRandomAPI {
    @GET("random.php")
    suspend fun getCocktailRandomAPI(): Response<CocktailResponse>
}