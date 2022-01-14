package br.com.iesb.maximaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class PegaPedido (
    val pedidos: List<Pedido>
)

@Entity(tableName = "pedido")
data class Pedido (
    @PrimaryKey (autoGenerate = true)
    val numero_ped_Rca: Long,
    val numero_ped_erp: String,
    val codigoCliente: String,
    val NOMECLIENTE: String,
    val data: String,
    val status: String,
    val critica: String? = null,
    val tipo: Tipo? = null,
    val legendas: List<String>? = null
)

enum class Tipo {
    Orcamento,
    Pedido
}
