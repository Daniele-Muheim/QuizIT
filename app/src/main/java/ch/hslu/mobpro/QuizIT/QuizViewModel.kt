package ch.hslu.mobpro.QuizIT

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

class QuizViewModel: ViewModel() {
    val question: MutableLiveData<List<QuestionSet?>> = MutableLiveData()
    val leaderboard: MutableLiveData<List<Leaderboard?>> = MutableLiveData()
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(OkHttpClient().newBuilder().build())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://apiurl.ch/quizIT/")
        .build()

    private val quizITAPIService = retrofit.create(QuizITAPIService::class.java)

    fun getQuestions() {
        val call = quizITAPIService.getQuestions()
        call.enqueue(object : Callback<QuestionSet> {
            override fun onResponse(call: Call<QuestionSet>, response: Response<QuestionSet>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    question.value = listOf(response.body())
                }
            }

            override fun onFailure(call: Call<QuestionSet>, t: Throwable) {
                Log.e(
                    "QuizViewModel|getQuestions",
                    t.localizedMessage ?: "call to API onFailure()"
                )
            }
        })
    }

    fun getLeaderBoard() {
        val call = quizITAPIService.getLeaderBoard()
        call.enqueue(object : Callback<Leaderboard> {
            override fun onResponse(call: Call<Leaderboard>, response: Response<Leaderboard>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    leaderboard.value = listOf(response.body())
                }
            }

            override fun onFailure(call: Call<Leaderboard>, t: Throwable) {
                Log.e(
                    "QuizViewModel|getQuestions",
                    t.localizedMessage ?: "call to API onFailure()"
                )
            }
        })
    }


    fun postLeaderBoard(score : Score, onResult: (Score?) -> Unit) {
        val call = quizITAPIService.postScore(score)

        call.enqueue(object : Callback<Score> {
            override fun onResponse(call: Call<Score>, response: Response<Score>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    val addScore = response.body()
                    onResult(addScore)
                }
            }

            override fun onFailure(call: Call<Score>, t: Throwable) {
                Log.e(
                    "QuizViewModel|postLeaderBoard",
                    t.localizedMessage ?: "call to API onFailure()"
                )
            }
        })
    }

    fun resetQuestionsData() {
        question.value = emptyList()
        leaderboard.value = emptyList()
    }

}