package by.zharikov.shared.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.util.*
import java.util.stream.Collectors

class Converter {
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromListToString(ingredientList: List<String?>): String {
        val ingredient = mutableListOf<String>()
        ingredientList.forEach {
            if (it != null) {
                if (it.isNotEmpty()) {
                    ingredient.add(it)
                }
            }
        }
        return ingredient.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun fromStringToList(ingredient: String): List<String> {
        return ingredient.trim().splitToSequence(",")
            .filter {
                it.isNotEmpty()
            }
            .toList()
    }
}