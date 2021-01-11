package by.zharikov.shared.data.db.cocktails.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.zharikov.shared.data.db.cocktails.dao.CocktailDao
import by.zharikov.shared.data.db.cocktails.entity.CocktailDBEntity
import by.zharikov.shared.utils.Converter

@Database(entities = [CocktailDBEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class CocktailDataBase : RoomDatabase() {
    abstract fun getDao():CocktailDao
}

object DataBaseProvider{
    fun provide(context: Context) =
        Room.databaseBuilder(context, CocktailDataBase::class.java, "CocktailDataBase").build()
}