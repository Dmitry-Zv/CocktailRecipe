package by.zharikov.shared.data.entity.cocktail

data class Cocktail(
    val drinkName: String,
    val drinkCategory: String,
    val isAlcoholic: String,
    val instruction: String,
    val imageUrl: String,
    val ingredientList: List<String?>
)