package by.zharikov.shared.data.dto.cocktail


import com.google.gson.annotations.SerializedName

data class CocktailResponse(
    @SerializedName("drinks")
    val drinks: List<Drink?>? = null
) {
    data class Drink(
        @SerializedName("dateModified")
        val dateModified: String? = null,
        @SerializedName("idDrink")
        val idDrink: String? = null,
        @SerializedName("strAlcoholic")
        val strAlcoholic: String? = null,
        @SerializedName("strCategory")
        val strCategory: String? = null,
        @SerializedName("strCreativeCommonsConfirmed")
        val strCreativeCommonsConfirmed: String? = null,
        @SerializedName("strDrink")
        val strDrink: String? = null,
        @SerializedName("strDrinkAlternate")
        val strDrinkAlternate: Any? = null,
        @SerializedName("strDrinkDE")
        val strDrinkDE: Any? = null,
        @SerializedName("strDrinkES")
        val strDrinkES: Any? = null,
        @SerializedName("strDrinkFR")
        val strDrinkFR: Any? = null,
        @SerializedName("strDrinkThumb")
        val strDrinkThumb: String? = null,
        @SerializedName("strDrinkZH-HANS")
        val strDrinkZHHANS: Any? = null,
        @SerializedName("strDrinkZH-HANT")
        val strDrinkZHHANT: Any? = null,
        @SerializedName("strGlass")
        val strGlass: String? = null,
        @SerializedName("strIBA")
        val strIBA: Any? = null,
        @SerializedName("strImageAttribution")
        val strImageAttribution: Any? = null,
        @SerializedName("strImageSource")
        val strImageSource: Any? = null,
        @SerializedName("strIngredient1")
        val strIngredient1: String? = null,
        @SerializedName("strIngredient10")
        val strIngredient10: String? = null,
        @SerializedName("strIngredient11")
        val strIngredient11: String? = null,
        @SerializedName("strIngredient12")
        val strIngredient12: String? = null,
        @SerializedName("strIngredient13")
        val strIngredient13: String? = null,
        @SerializedName("strIngredient14")
        val strIngredient14: String? = null,
        @SerializedName("strIngredient15")
        val strIngredient15: String? = null,
        @SerializedName("strIngredient2")
        val strIngredient2: String? = null,
        @SerializedName("strIngredient3")
        val strIngredient3: String? = null,
        @SerializedName("strIngredient4")
        val strIngredient4: String? = null,
        @SerializedName("strIngredient5")
        val strIngredient5: String? = null,
        @SerializedName("strIngredient6")
        val strIngredient6: String? = null,
        @SerializedName("strIngredient7")
        val strIngredient7: String? = null,
        @SerializedName("strIngredient8")
        val strIngredient8: String? = null,
        @SerializedName("strIngredient9")
        val strIngredient9: String? = null,
        @SerializedName("strInstructions")
        val strInstructions: String? = null,
        @SerializedName("strInstructionsDE")
        val strInstructionsDE: String? = null,
        @SerializedName("strInstructionsES")
        val strInstructionsES: Any? = null,
        @SerializedName("strInstructionsFR")
        val strInstructionsFR: Any? = null,
        @SerializedName("strInstructionsZH-HANS")
        val strInstructionsZHHANS: Any? = null,
        @SerializedName("strInstructionsZH-HANT")
        val strInstructionsZHHANT: Any? = null,
        @SerializedName("strMeasure1")
        val strMeasure1: String? = null,
        @SerializedName("strMeasure10")
        val strMeasure10: Any? = null,
        @SerializedName("strMeasure11")
        val strMeasure11: Any? = null,
        @SerializedName("strMeasure12")
        val strMeasure12: Any? = null,
        @SerializedName("strMeasure13")
        val strMeasure13: Any? = null,
        @SerializedName("strMeasure14")
        val strMeasure14: Any? = null,
        @SerializedName("strMeasure15")
        val strMeasure15: Any? = null,
        @SerializedName("strMeasure2")
        val strMeasure2: String? = null,
        @SerializedName("strMeasure3")
        val strMeasure3: String? = null,
        @SerializedName("strMeasure4")
        val strMeasure4: String? = null,
        @SerializedName("strMeasure5")
        val strMeasure5: Any? = null,
        @SerializedName("strMeasure6")
        val strMeasure6: Any? = null,
        @SerializedName("strMeasure7")
        val strMeasure7: Any? = null,
        @SerializedName("strMeasure8")
        val strMeasure8: Any? = null,
        @SerializedName("strMeasure9")
        val strMeasure9: Any? = null,
        @SerializedName("strTags")
        val strTags: Any? = null,
        @SerializedName("strVideo")
        val strVideo: Any? = null
    )
}