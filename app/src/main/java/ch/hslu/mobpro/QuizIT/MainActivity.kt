package ch.hslu.mobpro.QuizIT

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import ch.hslu.mobpro.QuizIT.fragments.HostFragment
import ch.hslu.mobpro.QuizIT.fragments.QuizQuestionsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.hostFragment, HostFragment.newInstance())
                .commit()
        }
    }
}