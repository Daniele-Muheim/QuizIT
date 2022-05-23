package ch.hslu.mobpro.QuizIT.roomDB

import androidx.room.*
import ch.hslu.mobpro.QuizIT.Score

@Dao
interface ScoreDao {

    @Query("Select * from score")
    fun getScore(): List<Score>

    @Insert
    fun insertScore(scoreList: List<Score>?)

    @Update
    fun updateScore(scoreList: List<Score>?)

}