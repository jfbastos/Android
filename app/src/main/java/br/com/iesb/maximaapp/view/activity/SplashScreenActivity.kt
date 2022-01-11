package br.com.iesb.maximaapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import br.com.iesb.maximaapp.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_sceen)

        Handler(Looper.getMainLooper()).postDelayed({
            vaiParaMenu()
        },2000)
    }

    private fun vaiParaMenu(){
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }
}