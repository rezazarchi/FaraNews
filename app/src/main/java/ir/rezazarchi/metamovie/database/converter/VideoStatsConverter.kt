package ir.rezazarchi.metamovie.database.converter

import androidx.room.TypeConverter
import ir.rezazarchi.metamovie.database.entity.VideoStats
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class VideoStatsConverter {
    @TypeConverter
    fun fromVideoStats(videoStats: VideoStats): String {
        return Json.encodeToString(videoStats)
    }

    @TypeConverter
    fun toVideoStats(videoStatsString: String): VideoStats {
        return Json.decodeFromString(videoStatsString)
    }
}