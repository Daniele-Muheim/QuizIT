package ch.hslu.mobpro.QuizIT

import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuizITAPIService {
    @GET("questionSet")
    fun getQuestions(): Call<List<Question>>

    @GET("leaderboard")
    fun getLeaderBoard(): Call<List<Score>>

    @POST("score")
    fun postScore(@Body score: Score): Call<Score>
}

data class Question (
    val _id: String,
    val question: String,
    val answerOne: String,
    val answerTwo: String,
    val answerThree: String,
    val answerFour: String?,
    val correctAnswer: Int,
    val questionSet: Int
)

@Entity
data class Score (
    @PrimaryKey(autoGenerate = true)
    val _id: Long = 0,
    val timeInMilliseconds: Long,
    val score: Int,
    val username: String
)