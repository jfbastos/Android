package br.com.iesb.maximaapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.iesb.maximaapp.databinding.FragmentDadosBinding
import br.com.iesb.maximaapp.model.Cliente
import br.com.iesb.maximaapp.view.adapters.ContatosAdapter
import br.com.iesb.maximaapp.viewmodel.ClienteViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class DadosFragment : Fragment() {


    private var _binding: FragmentDadosBinding? = null
    private val binding: FragmentDadosBinding get() = _binding!!
    private lateinit var adapter: ContatosAdapter
    private lateinit var status: String

    private val viewModel: ClienteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDadosBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pegaClientes()

        viewModel.clientesLiveData.observe(viewLifecycleOwner) { cliente ->
            cliente?.let {
                preencheCampos(it)
                status = cliente.status
            }
        }

        binding.botaoStatus.setOnClickListener {
            val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm")
            val currentDate = sdf.format(Date())

            Snackbar.make(requireView(), "$currentDate Status - $status", Snackbar.LENGTH_SHORT)
                .setAction("FECHAR") {}.show()
        }

    }

    private fun preencheCampos(cliente: Cliente) {
        binding.codigoCliente.text = cliente.codigo
        binding.razaoSocial.text = cliente.razao_social
        binding.nomeFantasia.text = cliente.nomeFantasia
        binding.cnpjCliente.text = cliente.cnpj
        binding.ramoCliente.text = cliente.ramo_atividade
        binding.enderecoCliente.text = cliente.endereco

        configuraRecyclerViewContatos(cliente)
    }

    private fun configuraRecyclerViewContatos(cliente: Cliente) {
        adapter = ContatosAdapter()
        binding.clientesRv.adapter = adapter
        adapter.differ.submitList(cliente.contatos)
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }

}