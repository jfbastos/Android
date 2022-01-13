package br.com.iesb.maximaapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.iesb.maximaapp.databinding.FragmentDadosBinding
import br.com.iesb.maximaapp.model.Cliente
import br.com.iesb.maximaapp.model.Repositorio
import br.com.iesb.maximaapp.model.network.InstanciaRetrofit
import br.com.iesb.maximaapp.view.adapters.ContatosAdapter
import br.com.iesb.maximaapp.viewmodel.ClienteViewModel
import br.com.iesb.maximaapp.viewmodel.fabrica.FabricaClienteViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class DadosFragment : Fragment() {


    private var _binding: FragmentDadosBinding? = null
    private val binding: FragmentDadosBinding get() = _binding!!
    private lateinit var adapter: ContatosAdapter
    private lateinit var status: String

    private val viewModel: ClienteViewModel by lazy {
        val viewModelProvider =
            FabricaClienteViewModel(repositorio = Repositorio(InstanciaRetrofit.servicoApi))
        ViewModelProvider(this, viewModelProvider)[ClienteViewModel::class.java]
    }

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

        viewModel.clientesLiveData.observe(viewLifecycleOwner) {
            val cliente = it.cliente

            preencheCampos(cliente)

            status = cliente.status

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