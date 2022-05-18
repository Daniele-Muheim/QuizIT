package ch.hslu.mobpro.QuizIT

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuizITAPIService {
    @GET("questionSet")
    fun getQuestions(): Call<List<Question>>

    @GET("leaderboard")
    fun getLeaderBoard(): Call<Leaderboard>

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

data class Score (
    val timeInMilliseconds: Int,
    val score: Int,
    val username: String
)
data class Leaderboard (
    val topTenScoresAsList: ArrayList<Score>
)
