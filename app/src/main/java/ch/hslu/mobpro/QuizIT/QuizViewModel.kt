package ch.hslu.mobpro.QuizIT

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import ch.hslu.mobpro.QuizIT.roomDB.AppDatabase
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection


class QuizViewModel(application: Application): AndroidViewModel(application) {
    var questionSet: MutableLiveData<List<Question>> = MutableLiveData()
    val leaderboard: MutableLiveData<List<Score>> = MutableLiveData()
    private val prefs = PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext)!!
    var startTimeStampInMilliSeconds: Long = 0

    private val appDb by lazy {
        AppDatabase.getDataBase(getApplication<Application>().applicationContext)
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(OkHttpClient().newBuilder().build())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("http://178.196.29.181:8085/api/v1/")
        .build()

    private val quizITAPIService = retrofit.create(QuizITAPIService::class.java)

    fun getQuestions() {
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
    }

    fun getLeaderBoard() {
        val call = quizITAPIService.getLeaderBoard()
        call.enqueue(object : Callback<List<Score>> {
            override fun onResponse(call: Call<List<Score>>, response: Response<List<Score>>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    leaderboard.value = response.body()

                    if (appDb.ScoreDao().getScore().isEmpty()) {
                        appDb.ScoreDao().insertScore(response.body())
                    } else {
                        appDb.ScoreDao().updateScore(response.body())
                    }
                    Log.i(
                        "RoomDB|getScore",
                        "Writing Data to local Room-DB: " + appDb.ScoreDao().getScore().toString()
                    )
                }
            }
            override fun onFailure(call: Call<List<Score>>, t: Throwable) {
                leaderboard.value = appDb.ScoreDao().getScore()
                Log.i("RoomDB|getScore", "getting Leaderboard from local Room-DB")
                Log.e(
                    "QuizViewModel|getLeaderBoard",
                    t.localizedMessage ?: "call to API onFailure()"
                )
            }
        })
    }


    fun postScore(score: Score) {
        val call = quizITAPIService.postScore(score)
        call.enqueue(object : Callback<Score> {
            override fun onResponse(call: Call<Score>, response: Response<Score>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
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

    fun startTimer() {
        startTimeStampInMilliSeconds = System.currentTimeMillis()
    }

    fun stopTimer(): Long {
        return System.currentTimeMillis() - startTimeStampInMilliSeconds
    }

}