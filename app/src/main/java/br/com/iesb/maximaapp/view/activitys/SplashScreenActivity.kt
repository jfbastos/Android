package br.com.iesb.maximaapp.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import br.com.iesb.maximaapp.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_sceen)

        val handle = Handler()
        handle.postDelayed({ Toast.makeText(this, "Teste", Toast.LENGTH_SHORT).show() }, 2000)

    }
}