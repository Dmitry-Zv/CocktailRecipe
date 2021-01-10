package by.zharikov.shared.repository.coctails

import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.data.mapper.cocktailmapper.CocktailMapper
import javax.inject.Inject

class CocktailRepository @Inject constructor(
    private val cocktailDataSource: CocktailDataSource,
    private val cocktailMapper: CocktailMapper
) {

    suspend fun getCocktails(firstLetter: String): List<Cocktail>? {
        val response = cocktailDataSource.getApi(firstLetter)
        if (response.isSuccessful) {
            return response.body()?.drinks?.map {
                cocktailMapper.map(it)
            }
        } else {
            throw Throwable(response.message())
        }
    }

    suspend fun getCocktailsByDrinkName(drinkName: String): List<Cocktail>? {
        val response = cocktailDataSource.getCocktailByDrinkNameAPI(drinkName)
        if (response.isSuccessful) {
            return response.body()?.drinks?.map {
                cocktailMapper.map(it)
            }
        } else {
            throw Throwable(response.message())
        }
    }
    suspend fun getCocktailRandom(): List<Cocktail>? {
        val response = cocktailDataSource.getCocktailRandomAPI()
        if (response.isSuccessful){
            return response.body()?.drinks?.map {
                cocktailMapper.map(it)
            }
        } else{
            throw Throwable(response.message())
        }
    }

}