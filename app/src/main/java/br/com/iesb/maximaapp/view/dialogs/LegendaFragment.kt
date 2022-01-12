package br.com.iesb.maximaapp.view.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.iesb.maximaapp.R
import br.com.iesb.maximaapp.databinding.FragmentLegendaBinding

class LegendaFragment : DialogFragment() {

    private var _binding : FragmentLegendaBinding? = null
    private val binding : FragmentLegendaBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLegendaBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding.botaoFechar.setOnClickListener {
            this.dismiss()
        }

        return super.onCreateDialog(savedInstanceState)
    }
}