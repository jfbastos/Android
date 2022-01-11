package br.com.iesb.maximaapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.iesb.maximaapp.R
import br.com.iesb.maximaapp.databinding.ActivityMenuPrincipalBinding

class MenuPrincipalActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.opcaoClientes.setOnClickListener {
            vaiParaClientes()
        }
    }

    private fun vaiParaClientes() {
        val intent = Intent(this, ClientesActivity::class.java)
        startActivity(intent)
    }
}