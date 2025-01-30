package ir.rezazarchi.metamovie.database.db

import android.content.Context
import androidx.room.Room

object DatabaseFactory {
    @Volatile
    private var INSTANCE: AppDataBase? = null

    fun getDatabase(context: Context): AppDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "app_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
