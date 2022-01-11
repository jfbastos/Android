package br.com.iesb.maximaapp.model.network

import br.com.iesb.maximaapp.model.Cliente
import br.com.iesb.maximaapp.model.PegaCliente
import retrofit2.Response
import retrofit2.http.GET

interface MaximaApi {

    companion object{
        const val BASE_URL = "https://private-anon-b1e0ede93c-maximatech.apiary-mock.com/android/"
    }

    @GET("cliente")
    suspend fun pegaClientes() : Response<PegaCliente>

}