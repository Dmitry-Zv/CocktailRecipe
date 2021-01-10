package by.zharikov.cocktails.ui.home

import android.os.Parcelable

data class HomeList(
    val categoryName: String
)

object MainWindow {
    fun get(): List<HomeList> {
        return listOf(
            HomeList("List all cocktails by first letter"),
            HomeList("Search cocktail by name"),
            HomeList("Search by ingredient")
        )
    }
}
