package ir.rezazarchi.metamovie.commonui.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun formatDate(instant: Instant): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}