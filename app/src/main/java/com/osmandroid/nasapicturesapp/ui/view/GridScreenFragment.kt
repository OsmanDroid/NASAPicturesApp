package com.osmandroid.nasapicturesapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.osmandroid.nasapicturesapp.R
import com.osmandroid.nasapicturesapp.databinding.FragmentGridScreenBinding
import com.osmandroid.nasapicturesapp.ui.viewmodel.NasaPicturesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GridScreenFragment : Fragment() {

    private var _binding: FragmentGridScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: NasaPicturesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGridScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}