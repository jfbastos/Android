package br.com.iesb.maximaapp.view.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.iesb.maximaapp.databinding.FragmentLegendaBinding

class LegendaFragment : DialogFragment() {

    private lateinit var binding : FragmentLegendaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLegendaBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botaoFechar.setOnClickListener {
            this.dismiss()
        }
    }

}