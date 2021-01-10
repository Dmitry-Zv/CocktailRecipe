package by.zharikov.shared.repository.coctails

import by.zharikov.shared.data.dto.cocktail.CocktailResponse
import by.zharikov.shared.network.cocktailapi.CocktailAPI
import retrofit2.Response
import javax.inject.Inject

class CocktailDataSource @Inject constructor(private val api: CocktailAPI) {

    suspend fun getApi(firstLetter: String): Response<CocktailResponse> {
        return api.getCocktailsByFirstLetter(firstLetter)
    }

}