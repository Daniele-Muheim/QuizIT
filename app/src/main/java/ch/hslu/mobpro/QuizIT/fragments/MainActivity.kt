package ch.hslu.mobpro.QuizIT.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ch.hslu.mobpro.QuizIT.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.hostFragment, HostFragment.newInstance())
                .commitNow()
        }
    }
}