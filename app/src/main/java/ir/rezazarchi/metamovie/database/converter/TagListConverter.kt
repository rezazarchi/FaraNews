package ir.rezazarchi.metamovie.database.converter

import androidx.room.TypeConverter

class TagListConverter {
    @TypeConverter
    fun fromTagList(tags: List<String>): String {
        return tags.joinToString(",")
    }

    @TypeConverter
    fun toTagList(tagsString: String): List<String> {
        return tagsString.split(",").map { it.trim() }
    }
}