package ir.rezazarchi.metamovie.database.converter

import androidx.room.TypeConverter
import java.time.Instant

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: String?): Instant? {
        return value?.let { Instant.parse(it) }
    }

    @TypeConverter
    fun instantToTimestamp(instant: Instant?): String? {
        return instant?.toString()
    }
}