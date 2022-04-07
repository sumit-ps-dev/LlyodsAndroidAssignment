package com.android.mvvm_cleanarchitecture.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.mvvm_cleanarchitecture.R
import com.android.mvvm_cleanarchitecture.data.response.Artist
import com.android.mvvm_cleanarchitecture.databinding.FragmentTopArtistBinding
import com.android.mvvm_cleanarchitecture.showToast
import com.android.mvvm_cleanarchitecture.utility.ItemSpaceDecoration
import com.android.mvvm_cleanarchitecture.viewmodel.TopArtistViewModel
import com.android.mvvm_cleanarchitecture.viewmodel.ViewState
import com.android.mvvm_cleanarchitecture.views.activities.MainActivity
import com.android.mvvm_cleanarchitecture.views.adapter.ItemClickListener
import com.android.mvvm_cleanarchitecture.views.adapter.TopArtistAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopArtistFragment : Fragment() {

    private lateinit var binding: FragmentTopArtistBinding
    private val viewModel: TopArtistViewModel by viewModels()

    private val artistItemClickListener: ItemClickListener<Artist> = { artist ->
        findNavController().navigate(
            R.id.action_topArtistFragment_to_artistDetailFragment,
            Bundle().apply {
                putString(ARTIST_URL, artist.url)
            })


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTopArtistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvArtists.addItemDecoration(ItemSpaceDecoration(
            resources.getDimension(R.dimen.default_padding).toInt()))
        viewModel.artistLiveData.observe(viewLifecycleOwner, Observer { viewState ->

            when (viewState) {

                is ViewState.Loading -> {
                    binding.progressBar.visibility =
                        if (viewState.isLoading) View.VISIBLE else View.GONE
                }

                is ViewState.Failure -> {
                    viewState.throwable.message?.let {
                        context?.showToast(it)
                    }

                }

                is ViewState.Success -> {
                    val data = viewState.output

                    if (binding.rvArtists.adapter == null) {
                        binding.rvArtists.adapter = TopArtistAdapter(artistItemClickListener)
                    }

                    (binding.rvArtists.adapter as TopArtistAdapter).itemList = data
                }

            }
        })
        viewModel.fetchTopArists()

    }


}