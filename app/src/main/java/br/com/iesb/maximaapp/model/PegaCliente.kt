package br.com.iesb.maximaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


data class PegaCliente (
    val cliente: Cliente
) : Serializable

@Entity(tableName = "cliente")
data class Cliente (
    @PrimaryKey val id: Long,
    val codigo: String,
    val razao_social: String,
    val nomeFantasia: String,
    val cnpj: String,
    val ramo_atividade: String,
    val endereco: String,
    val status: String,
    val contatos: List<Contato>
) : Serializable


data class Contato (
    val nome: String,
    val telefone: String,
    val celular: String?,
    val conjuge: String?,
    val tipo: String?,
    val time: String?,
    val e_mail: String?,
    val data_nascimento: String,
    val dataNascimentoConjuge: String? = null
) : Serializable



