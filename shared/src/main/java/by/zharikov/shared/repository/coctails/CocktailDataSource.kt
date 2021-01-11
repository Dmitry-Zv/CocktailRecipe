package by.zharikov.shared.repository.coctails

import by.zharikov.shared.data.db.cocktails.dao.CocktailDao
import by.zharikov.shared.data.db.cocktails.entity.CocktailDBEntity
import by.zharikov.shared.data.dto.cocktail.CocktailResponse
import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.network.cocktailapi.CocktailAPI
import by.zharikov.shared.network.cocktailapi.CocktailByNameDrinkAPI
import by.zharikov.shared.network.cocktailapi.CocktailRandomAPI
import retrofit2.Response
import javax.inject.Inject

class CocktailDataSource @Inject constructor(
    private val api: CocktailAPI,
    private val cocktailByDrinkNameAPI: CocktailByNameDrinkAPI,
    private val cocktailRandomAPI: CocktailRandomAPI
) {

    suspend fun getApi(firstLetter: String): Response<CocktailResponse> {
        return api.getCocktailsByFirstLetter(firstLetter)
    }

    suspend fun getCocktailByDrinkNameAPI(drinkName: String): Response<CocktailResponse> {
        return cocktailByDrinkNameAPI.getCocktailByNameDrinkAPI(drinkName)
    }

    suspend fun getCocktailRandomAPI(): Response<CocktailResponse> {
        return cocktailRandomAPI.getCocktailRandomAPI()
    }

    suspend fun insertCocktailToDB(dao: CocktailDao, cocktailDBEntity: CocktailDBEntity) {
        dao.insert(cocktailDBEntity)
    }

    suspend fun getCocktailList(dao: CocktailDao) =
        dao.getListCocktailDBEntity()

    suspend fun deleteCocktail(dao: CocktailDao, cocktailDBEntity: CocktailDBEntity) {
        dao.delete(cocktailDBEntity)
    }

    suspend fun check(dao: CocktailDao, cocktail: Cocktail):CocktailDBEntity?{
       return dao.check(cocktail.drinkName)
    }

}