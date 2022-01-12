package br.com.iesb.maximaapp.model

data class PegaPedido (
    val pedidos: List<Pedido>
)

data class Pedido (
    val numero_ped_Rca: Long,
    val numero_ped_erp: String,
    val codigoCliente: String,
    val NOMECLIENTE: String,
    val data: String,
    val status: String,
    val critica: String? = null,
    val tipo: Tipo,
    val legendas: List<String>? = null
)

enum class Tipo {
    Orcamento,
    Pedido
}
