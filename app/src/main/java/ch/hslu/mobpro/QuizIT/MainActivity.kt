package ch.hslu.mobpro.QuizIT

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ch.hslu.mobpro.QuizIT.fragments.HostFragment

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