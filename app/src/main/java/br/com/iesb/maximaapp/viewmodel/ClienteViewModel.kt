package br.com.iesb.maximaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.iesb.maximaapp.model.Cliente
import br.com.iesb.maximaapp.model.PegaCliente
import br.com.iesb.maximaapp.model.RepositorioCliente
import kotlinx.coroutines.launch

class ClienteViewModel(private val respositorio: RepositorioCliente) : ViewModel() {

    private var _clientesLiveData = MutableLiveData<PegaCliente>()
    val clientesLiveData: LiveData<PegaCliente> get() = _clientesLiveData

    fun pegaClientes() = viewModelScope.launch {
        val resposta = respositorio.pegaClientes()

        if(resposta.isSuccessful){
            val clientes = resposta.body()
            print(clientes?.cliente?.cnpj)
            clientes?.let {
                _clientesLiveData.postValue(it)
            }
        }
    }


}