package br.com.iesb.maximaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.iesb.maximaapp.model.PegaCliente
import br.com.iesb.maximaapp.model.PegaPedido
import br.com.iesb.maximaapp.model.Repositorio
import kotlinx.coroutines.launch

class ClienteViewModel(private val respositorio: Repositorio) : ViewModel() {

    private var _clientesLiveData = MutableLiveData<PegaCliente>()
    val clientesLiveData: LiveData<PegaCliente> get() = _clientesLiveData

    private var _pedidosLiveData = MutableLiveData<PegaPedido>()
    val pedidosLiveData : LiveData<PegaPedido> get() = _pedidosLiveData

    fun pegaClientes() = viewModelScope.launch {
        val resposta = respositorio.pegaClientes()

        if(resposta.isSuccessful){
            val clientes = resposta.body()
            clientes?.let {
                _clientesLiveData.postValue(it)
            }
        }
    }

    fun pegaPedidosCliente() = viewModelScope.launch {
        val resposta = respositorio.pegaPedidos()

        if(resposta.isSuccessful){
            val pedidos = resposta.body()
            pedidos?.let {
                _pedidosLiveData.postValue(it)
            }
        }
    }


}