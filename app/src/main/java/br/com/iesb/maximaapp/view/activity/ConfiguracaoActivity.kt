package br.com.iesb.maximaapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.iesb.maximaapp.R
import br.com.iesb.maximaapp.databinding.ActivityConfiguracaoBinding

class ConfiguracaoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfiguracaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfiguracaoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_configuracao)

        binding.botaoLembrete.setOnClickListener {

        }
    }


}