package br.com.iesb.maximaapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.iesb.maximaapp.databinding.ItemContatosBinding
import br.com.iesb.maximaapp.model.Contato

class ContatosAdapter : ListAdapter<Contato, ContatosAdapter.ContatosViewHolder>(differCallback) {

    inner class ContatosViewHolder(private val binding : ItemContatosBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(contato : Contato){
            preencheDados(contato)
        }

        private fun preencheDados(contato: Contato) {
            binding.nomeContato.text = contato.nome
            binding.telefoneContato.text = contato.telefone
            binding.celularContato.text = contato.celular
            binding.tipoContato.text = contato.tipo
            binding.conjugeContato.text = contato.conjuge ?: "Não informado"
            binding.timeContato.text = contato.tipo ?: "Não informado"
            binding.hobbieContato.text = "Não informado"
            binding.emailContato.text = contato.e_mail ?: "Não informado"
            binding.dataNascimentoContato.text = contato.data_nascimento
            binding.nascimentoConjugeContato.text = contato.dataNascimentoConjuge ?: "Não informado"
            binding.timeContato.text = contato.time
        }
    }

    companion object{
        private val differCallback : DiffUtil.ItemCallback<Contato> = object : DiffUtil.ItemCallback<Contato>(){
            override fun areItemsTheSame(oldItem: Contato, newItem: Contato): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Contato, newItem: Contato): Boolean {
                return oldItem.celular == newItem.celular
            }
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatosViewHolder {
        val binding = ItemContatosBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return ContatosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContatosViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}