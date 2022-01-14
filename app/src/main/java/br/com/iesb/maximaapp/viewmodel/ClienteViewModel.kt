package br.com.iesb.maximaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.iesb.maximaapp.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(private val repositorio: Repositorio) : ViewModel() {

    private var _clientesLiveData = MutableLiveData<Cliente>()
    val clientesLiveData: LiveData<Cliente> get() = _clientesLiveData

    private var _pedidosLiveData = MutableLiveData<List<Pedido>>()
    val pedidosLiveData : LiveData<List<Pedido>> get() = _pedidosLiveData

    fun pegaClientes() = viewModelScope.launch {
        val resposta = repositorio.pegaClienteApi()

        if(resposta.isSuccessful){
            val clientes = resposta.body()
            clientes?.let {
                _clientesLiveData.postValue(it.cliente)
                repositorio.salvaClienteBanco(it.cliente)
            }
        }else{
            CoroutineScope(Dispatchers.IO).launch {
                _clientesLiveData.postValue(repositorio.pegaClienteBanco())
            }

        }
    }

    fun pegaPedidosCliente() = viewModelScope.launch {
        val resposta = repositorio.pegaPedidosApi()

        if(resposta.isSuccessful){
            val pedidos = resposta.body()
            pedidos?.let {
                _pedidosLiveData.postValue(it.pedidos)
                repositorio.salvaPedidosBanco(it.pedidos)
            }
        }else{
            CoroutineScope(Dispatchers.IO).launch {
                _pedidosLiveData.postValue(repositorio.pegaPedidosBanco())
            }
        }
    }


}