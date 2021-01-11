package by.zharikov.shared.data.db.cocktails.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CocktailDBEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    val drinkName: String,
    val drinkCategory: String,
    val isAlcoholic: String,
    val instruction: String,
    val imageUrl: String,
    val ingredientList: List<String?>
)