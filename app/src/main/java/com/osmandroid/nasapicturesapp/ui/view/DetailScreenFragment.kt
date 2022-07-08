package com.osmandroid.nasapicturesapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.osmandroid.nasapicturesapp.R
import com.osmandroid.nasapicturesapp.databinding.FragmentDetailScreenBinding
import com.osmandroid.nasapicturesapp.ui.adapter.DetailViewPagerAdapter
import com.osmandroid.nasapicturesapp.ui.viewmodel.NasaPicturesViewModel
import com.osmandroid.nasapicturesapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreenFragment : Fragment() {

    private var _binding: FragmentDetailScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val detailsPagerAdapter: DetailViewPagerAdapter by lazy { DetailViewPagerAdapter() }
    private val viewModel: NasaPicturesViewModel by activityViewModels()
    private val args: DetailScreenFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupViews() = with(binding) {
        viewPager.apply {
            adapter = detailsPagerAdapter
            setCurrentItem(args.position, false)
        }
    }

    private fun setupObservers() = with(binding) {
        viewModel.picturesList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    detailsPagerAdapter.submitList(result.value)
                    viewPager.apply {
                        setCurrentItem(args.position, false)
                    }
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