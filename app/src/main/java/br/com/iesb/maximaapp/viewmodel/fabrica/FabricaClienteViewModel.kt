package br.com.iesb.maximaapp.viewmodel.fabrica

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.iesb.maximaapp.model.RepositorioCliente
import br.com.iesb.maximaapp.viewmodel.ClienteViewModel

class FabricaClienteViewModel(private val repositorio : RepositorioCliente): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClienteViewModel(repositorio) as T
    }

}