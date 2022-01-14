package br.com.iesb.maximaapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.iesb.maximaapp.model.Cliente
import br.com.iesb.maximaapp.model.Pedido
import dagger.Provides


@Database(entities = [Cliente::class, Pedido::class], version=3)
@TypeConverters(ConversorContatos::class, ConversorLegendas::class)
abstract  class AppDatabase : RoomDatabase(){


    abstract fun clienteDao() : ClienteDao


    abstract fun pedidoDao() : PedidoDao
}