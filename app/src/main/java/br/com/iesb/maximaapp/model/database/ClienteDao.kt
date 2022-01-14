package br.com.iesb.maximaapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.iesb.maximaapp.model.Cliente

@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvaCliente(cliente : Cliente)

    @get : Query("SELECT * FROM cliente")
    val cliente : Cliente

}