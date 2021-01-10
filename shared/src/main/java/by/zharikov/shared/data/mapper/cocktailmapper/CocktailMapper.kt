package by.zharikov.shared.data.mapper.cocktailmapper

import by.zharikov.shared.data.dto.cocktail.CocktailResponse
import by.zharikov.shared.data.entity.cocktail.Cocktail
import javax.inject.Inject

class CocktailMapper @Inject constructor(){
    fun map(from: CocktailResponse.Drink?): Cocktail {
        return Cocktail(
            drinkName = from?.strDrink.orEmpty(),
            drinkCategory = from?.strCategory.orEmpty(),
            isAlcoholic = from?.strAlcoholic.orEmpty(),
            instruction = from?.strInstructions.orEmpty(),
            imageUrl = from?.strDrinkThumb.orEmpty(),
            ingredientList = listOf(
                from?.strIngredient1,
                from?.strIngredient2,
                from?.strIngredient3,
                from?.strIngredient4,
                from?.strIngredient5,
                from?.strIngredient6,
                from?.strIngredient7,
                from?.strIngredient8,
                from?.strIngredient9,
                from?.strIngredient10,
                from?.strIngredient11,
                from?.strIngredient12,
                from?.strIngredient13,
                from?.strIngredient14,
                from?.strIngredient15
            )
        )
    }
}