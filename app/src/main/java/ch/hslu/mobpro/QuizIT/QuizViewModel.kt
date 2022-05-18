package ch.hslu.mobpro.QuizIT

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

class QuizViewModel(application: Application): AndroidViewModel(application) {
    var questionSet: MutableLiveData<List<Question>> = MutableLiveData()
    val leaderboard: MutableLiveData<List<Score?>> = MutableLiveData()
    val prefs = PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext)
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(OkHttpClient().newBuilder().build())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("http://192.168.1.130:8085/api/v1/")
        .build()

    private val quizITAPIService = retrofit.create(QuizITAPIService::class.java)

    fun getQuestions(): MutableLiveData<List<Question>> {
        val call = quizITAPIService.getQuestions()
        call.enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    Log.i("api-call", response.body().toString() )
                    questionSet.value = response.body().orEmpty()
                }
            }
            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                Log.e(
                    "QuizViewModel|getQuestions",
                    t.localizedMessage ?: "call to API onFailure()"
                )
            }
        })
        return questionSet
    }

    fun getLeaderBoard() {
        val call = quizITAPIService.getLeaderBoard()
        call.enqueue(object : Callback<Leaderboard> {
            override fun onResponse(call: Call<Leaderboard>, response: Response<Leaderboard>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    leaderboard.value = response.body()?.topTenScoresAsList
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
        questionSet.value = emptyList()
        leaderboard.value = emptyList()
    }



    fun setUsername(username: String){
        val editor = prefs.edit()
        editor.putString("USERNAME", username)
        editor.apply()
    }

    fun getUsername(): String? {
        return prefs.getString("USERNAME", "Benutzername")
    }

}