package br.com.iesb.maximaapp.model.network

object ChamadaApiSegura {

    suspend fun <T> requestSeguro(
        requisicao: suspend () -> T
    ): T? {
        return try {
            requisicao()
        } catch (e: Exception) {
            println(e.localizedMessage)
            null
        }
    }
}