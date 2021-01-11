package by.zharikov.shared.data.mapper.cocktailmapper

import by.zharikov.shared.data.db.cocktails.entity.CocktailDBEntity
import by.zharikov.shared.data.entity.cocktail.Cocktail
import javax.inject.Inject

class CocktailDBtoCocktailMapper @Inject constructor() {
    fun map(from: CocktailDBEntity): Cocktail {
        return Cocktail(
            drinkName = from.drinkName,
            drinkCategory = from.drinkCategory,
            isAlcoholic = from.isAlcoholic,
            instruction = from.instruction,
            imageUrl = from.imageUrl,
            ingredientList = from.ingredientList
        )
    }
}