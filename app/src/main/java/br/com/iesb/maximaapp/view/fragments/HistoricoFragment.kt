package br.com.iesb.maximaapp.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.iesb.maximaapp.R
import br.com.iesb.maximaapp.databinding.FragmentHistoricoBinding
import br.com.iesb.maximaapp.model.Pedido
import br.com.iesb.maximaapp.model.Repositorio
import br.com.iesb.maximaapp.model.network.InstanciaRetrofit
import br.com.iesb.maximaapp.view.adapters.PedidosAdapter
import br.com.iesb.maximaapp.view.dialogs.LegendaFragment
import br.com.iesb.maximaapp.viewmodel.ClienteViewModel
import br.com.iesb.maximaapp.viewmodel.fabrica.FabricaClienteViewModel


class HistoricoFragment : Fragment() {

    private var _binding : FragmentHistoricoBinding? = null
    private val binding : FragmentHistoricoBinding get() = _binding!!
    private lateinit var adapter : PedidosAdapter

    private val viewModel : ClienteViewModel by lazy{
        val viewModelProvider = FabricaClienteViewModel(repositorio = Repositorio(InstanciaRetrofit.servicoApi))
        ViewModelProvider(this, viewModelProvider)[ClienteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoricoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pegaPedidosCliente()

        viewModel.pedidosLiveData.observe(viewLifecycleOwner){
            adapter = PedidosAdapter(requireContext())

            binding.historicoRv.adapter = adapter

            adapter.differ.submitList(it.pedidos)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.legendas_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.pesquisa -> {

            }
            R.id.legenda -> {
                LegendaFragment().show(parentFragmentManager, "legenda")
            }
            else -> {}
        }



        return super.onOptionsItemSelected(item)
    }



}