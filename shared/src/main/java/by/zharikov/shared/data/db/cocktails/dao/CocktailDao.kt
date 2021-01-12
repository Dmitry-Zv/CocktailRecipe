package by.zharikov.shared.data.db.cocktails.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import by.zharikov.shared.data.db.cocktails.entity.CocktailDBEntity
import retrofit2.http.DELETE

@Dao
interface CocktailDao {
    @Insert
    suspend fun insert(cocktailDBEntity: CocktailDBEntity)

    @Query("SELECT * FROM CocktailDBEntity ORDER BY drinkName")
    suspend fun getListCocktailDBEntity(): List<CocktailDBEntity>

    @Query("DELETE FROM CocktailDBEntity WHERE drinkName = :drinkName")
    suspend fun delete(drinkName: String)

    @Query("SELECT * FROM CocktailDBEntity WHERE drinkName = :drinkName")
    suspend fun check(drinkName: String):CocktailDBEntity?
}