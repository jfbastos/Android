package br.com.iesb.maximaapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.iesb.maximaapp.model.Pedido


@Dao
interface PedidoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvaPedido(pedido : Pedido)

    @get: Query("SELECT * FROM pedido")
    val pedidos : List<Pedido>


}