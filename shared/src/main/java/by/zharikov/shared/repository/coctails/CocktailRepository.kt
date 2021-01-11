package by.zharikov.shared.repository.coctails

import by.zharikov.shared.data.db.cocktails.dao.CocktailDao
import by.zharikov.shared.data.db.cocktails.entity.CocktailDBEntity
import by.zharikov.shared.data.entity.cocktail.Cocktail
import by.zharikov.shared.data.mapper.cocktailmapper.CocktailDBtoCocktailMapper
import by.zharikov.shared.data.mapper.cocktailmapper.CocktailMapper
import by.zharikov.shared.data.mapper.cocktailmapper.CocktailToCocktailDBMapper
import javax.inject.Inject

class CocktailRepository @Inject constructor(
    private val cocktailDataSource: CocktailDataSource,
    private val cocktailMapper: CocktailMapper,
    private val cocktailDBToCocktailMapper: CocktailDBtoCocktailMapper,
    private val cocktailToCocktailDBMapper: CocktailToCocktailDBMapper
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
        if (response.isSuccessful) {
            return response.body()?.drinks?.map {
                cocktailMapper.map(it)
            }
        } else {
            throw Throwable(response.message())
        }
    }

    suspend fun insert(dao: CocktailDao, cocktail: Cocktail) {
        val cocktailDB = cocktail.let {
            cocktailToCocktailDBMapper.map(it)
        }
        cocktailDataSource.insertCocktailToDB(dao, cocktailDB)
    }

    suspend fun getCocktailDb(dao: CocktailDao): List<Cocktail> {
        return cocktailDataSource.getCocktailList(dao).map {
            cocktailDBToCocktailMapper.map(it)
        }
    }

    suspend fun delete(dao: CocktailDao, cocktail: Cocktail) {
        val cocktailDB = cocktail.let {
            cocktailToCocktailDBMapper.map(it)
        }
        cocktailDataSource.deleteCocktail(dao, cocktailDB)
    }

    suspend fun check(dao: CocktailDao, cocktail: Cocktail):CocktailDBEntity?{
        return cocktailDataSource.check(dao, cocktail)
    }

}