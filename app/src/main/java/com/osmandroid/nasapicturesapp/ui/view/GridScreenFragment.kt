package com.osmandroid.nasapicturesapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.osmandroid.nasapicturesapp.R
import com.osmandroid.nasapicturesapp.databinding.FragmentGridScreenBinding
import com.osmandroid.nasapicturesapp.ui.adapter.GridListAdapter
import com.osmandroid.nasapicturesapp.ui.viewmodel.NasaPicturesViewModel
import com.osmandroid.nasapicturesapp.utils.Extensions.visible
import com.osmandroid.nasapicturesapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GridScreenFragment : Fragment() {

    private var _binding: FragmentGridScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: NasaPicturesViewModel by activityViewModels()

    private val gridListAdapter: GridListAdapter by lazy {
        GridListAdapter(onClick = { view, position ->
            val extras = FragmentNavigatorExtras(view to view.transitionName)
            val action = GridScreenFragmentDirections.actionGridFragmentToDetailFragment(position)
            findNavController().navigate(action, extras)
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGridScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }


    private fun setupViews() = with(binding) {
        recyclerView.apply {
            adapter = gridListAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupObservers() = with(binding) {
        viewModel.picturesList.observe(viewLifecycleOwner) { result ->
            progressBar.visible(result is Resource.Loading)
            when (result) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    gridListAdapter.submitList(result.value)
                }
                is Resource.Failure -> {

                    Snackbar.make(
                        root, getString(R.string.unable_to_load_data),
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction(getString(R.string.retry)) {
                        viewModel.getPicturesList()
                    }.show()

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}