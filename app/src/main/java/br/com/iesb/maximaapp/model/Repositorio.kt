package br.com.iesb.maximaapp.model

import br.com.iesb.maximaapp.model.database.ClienteDao
import br.com.iesb.maximaapp.model.database.PedidoDao
import br.com.iesb.maximaapp.model.network.ChamadaApiSegura
import br.com.iesb.maximaapp.model.network.MaximaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class Repositorio @Inject constructor(
    private val servico: MaximaApi,
    private val clienteDao: ClienteDao,
    private val pedidoDao: PedidoDao
) {

    suspend fun pegaClienteApi(): Response<PegaCliente> {
        return ChamadaApiSegura.requestSeguro {
            servico.pegaClientes()
        } ?: Response.success(null)
    }

    suspend fun pegaPedidosApi(): Response<PegaPedido> {
        return ChamadaApiSegura.requestSeguro {
            servico.pegaPedidos()
        } ?: Response.success(null)
    }

    fun pegaClienteBanco() = clienteDao.cliente

    fun pegaPedidosBanco() = pedidoDao.pedidos

    fun salvaClienteBanco(cliente: Cliente) {
        CoroutineScope(Dispatchers.IO).launch {
            clienteDao.salvaCliente(cliente)
        }

    }

    fun salvaPedidosBanco(pedidos: List<Pedido>) {
        CoroutineScope(Dispatchers.IO).launch {
            pedidos.forEach { pedido ->
                pedidoDao.salvaPedido(pedido)
            }
        }

    }


}