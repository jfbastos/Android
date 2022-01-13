package br.com.iesb.maximaapp.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
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

    private var _binding: FragmentHistoricoBinding? = null
    private val binding: FragmentHistoricoBinding get() = _binding!!
    private lateinit var adapter: PedidosAdapter
    private var listaOrigital = listOf<Pedido>()

    private val viewModel: ClienteViewModel by lazy {
        val viewModelProvider =
            FabricaClienteViewModel(repositorio = Repositorio(InstanciaRetrofit.servicoApi))
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

        pesquisa()

        configuraRecyclerView()

        viewModel.pedidosLiveData.observe(viewLifecycleOwner) {
            listaOrigital = it.pedidos

            adapter.differ.submitList(it.pedidos)
        }
    }

    private fun pesquisa() {
        binding.campoPesquisa.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                val searched = listaOrigital.filter {
                    it.NOMECLIENTE.uppercase().contains(query.uppercase())
                }

                adapter.differ.submitList(searched)
                return false
            }

            override fun onQueryTextChange(nextText: String): Boolean {
                val searched = listaOrigital.filter {
                    it.NOMECLIENTE.uppercase().contains(nextText.uppercase())
                }
                adapter.differ.submitList(searched)
                return false
            }
        })
    }

    private fun configuraRecyclerView() {
        adapter = PedidosAdapter(requireContext())
        binding.historicoRv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.legendas_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pesquisa -> {
                if (binding.campoPesquisa.isVisible) {
                    binding.campoPesquisa.visibility = View.GONE
                } else {
                    binding.campoPesquisa.visibility = View.VISIBLE
                }
           }
            R.id.legenda -> {
                LegendaFragment().show(parentFragmentManager, "legenda")
            }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}