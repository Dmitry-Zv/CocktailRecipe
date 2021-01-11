package by.zharikov.cocktails.ui.home

import android.os.Parcelable
import by.zharikov.cocktails.R

data class HomeList(
    val categoryName: String,
    val image: Int
)

object MainWindow {
    fun get(): List<HomeList> {
        return listOf(
            HomeList("List all cocktails by first letter", R.drawable.cocktail_by_first_letter),
            HomeList("Search cocktail by name", R.drawable.cocktail_by_name),
            HomeList("Random Cocktail", R.drawable.random_cocktail)
        )
    }
}
