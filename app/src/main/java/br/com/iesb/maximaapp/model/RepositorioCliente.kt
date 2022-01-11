package br.com.iesb.maximaapp.model

import br.com.iesb.maximaapp.model.network.ChamadaApiSegura
import br.com.iesb.maximaapp.model.network.MaximaApi
import retrofit2.Response

class RepositorioCliente(private val servico : MaximaApi) {

    suspend fun pegaClientes(): Response<PegaCliente> {
        return ChamadaApiSegura.requestSeguro {
            servico.pegaClientes()
        } ?: Response.error(1,null)
    }



}