package by.zharikov.shared.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.util.*
import java.util.stream.Collectors

class Converter {
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromListToString(ingredientList: List<String>): String {
        return ingredientList.stream().collect(Collectors.joining(","))
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