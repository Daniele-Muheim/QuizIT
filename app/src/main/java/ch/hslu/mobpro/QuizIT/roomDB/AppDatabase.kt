package ch.hslu.mobpro.QuizIT.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ch.hslu.mobpro.QuizIT.Score

@Database(entities = [Score::class], exportSchema = true, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ScoreDao(): ScoreDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                return INSTANCE
                    ?: Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase:: class.java,
                        "score-database"
                )
                        .allowMainThreadQueries()
                        .build()
            }
        }
    }
}