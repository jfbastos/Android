package br.com.iesb.maximaapp.view.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.iesb.maximaapp.R
import br.com.iesb.maximaapp.databinding.ItemHistoricoBinding
import br.com.iesb.maximaapp.model.Pedido
import java.text.SimpleDateFormat

class PedidosAdapter(val context : Context) : ListAdapter<Pedido, PedidosAdapter.PedidosViewHolder>(differCallback) {

    inner class PedidosViewHolder(private val binding : ItemHistoricoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (pedido: Pedido){
            binding.iconeStatus.background = ContextCompat.getDrawable(context, R.drawable.liberado)
            binding.iconeStatus.text = "L"
            binding.numeroPedido.text = "${pedido.numero_ped_Rca} / ${pedido.numero_ped_erp}"
            binding.clienteNome.text = "${pedido.codigoCliente} - ${pedido.NOMECLIENTE}"
            binding.status.text = pedido.status
            binding.horaPedido.text = pegaHora(pedido.data)
            binding.valorPedido.text = "-"

            when (pedido.critica){
                "SUCESSO" -> binding.critica.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_maxima_critica_sucesso))
                "FALHA_PARCIAL" -> binding.critica.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_maxima_critica_alerta))
                else -> binding.critica.visibility = View.GONE
            }

            when (pedido.legendas?.size){
                1 -> {
                    binding.legenda3.visibility = View.VISIBLE
                    binding.legenda3.setImageDrawable(defineImagemLegenda(pedido.legendas[0]))
                }
                2 -> {
                    binding.legenda3.visibility = View.VISIBLE
                    binding.legenda2.visibility = View.VISIBLE
                    binding.legenda3.setImageDrawable(defineImagemLegenda(pedido.legendas[0]))
                    binding.legenda2.setImageDrawable(defineImagemLegenda(pedido.legendas[1]))
                }
                3 -> {
                    binding.legenda3.visibility = View.VISIBLE
                    binding.legenda2.visibility = View.VISIBLE
                    binding.legenda1.visibility = View.VISIBLE
                    binding.legenda3.setImageDrawable(defineImagemLegenda(pedido.legendas[0]))
                    binding.legenda2.setImageDrawable(defineImagemLegenda(pedido.legendas[1]))
                    binding.legenda1.setImageDrawable(defineImagemLegenda(pedido.legendas[2]))
                }
            }


        }

        private fun defineImagemLegenda(legenda: String): Drawable? {
            return when(legenda){
                "PEDIDO_SOFREU_CORTE" -> ContextCompat.getDrawable(context, R.drawable.ic_maxima_legenda_corte)
                "PEDIDO_FEITO_TELEMARKETING" -> ContextCompat.getDrawable(context, R.drawable.ic_maxima_legenda_telemarketing)
                "PEDIDO_CANCELADO_ERP" -> ContextCompat.getDrawable(context, R.drawable.ic_maxima_legenda_cancelamento)
                else -> null
            }
        }

        private fun pegaHora(data: String): String {
            return data.substring(11,16)
        }
    }

    companion object{
        private val differCallback : DiffUtil.ItemCallback<Pedido> = object : DiffUtil.ItemCallback<Pedido>() {
            override fun areItemsTheSame(oldItem: Pedido, newItem: Pedido): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Pedido, newItem: Pedido): Boolean {
                return oldItem.numero_ped_Rca == newItem.numero_ped_Rca
            }
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosViewHolder {
        val binding = ItemHistoricoBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        )
        return PedidosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}