package ch.hslu.mobpro.QuizIT

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuizITAPIService {
    @GET("questionSet")
    fun getQuestions(): Call<QuestionSet>

    @GET("leaderboard")
    fun getLeaderBoard(): Call<Leaderboard>

    @POST("score")
    fun postScore(@Body score: Score): Call<Score>
}

data class Question (
    val id: Int,
    val question: String,
    val answerOne: String,
    val answerTwo: String,
    val answerThree: String,
    val answerFour: String?,
    val correctAnswer: Int
)

data class QuestionSet (
    val setOfQuestions: List<Question>
)

data class Score (
    val timeInMilliseconds: Int,
    val score: Int
)
data class Leaderboard (
    val topTenScoresAsList: List<Score>
)
