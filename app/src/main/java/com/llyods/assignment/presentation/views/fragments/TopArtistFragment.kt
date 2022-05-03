package com.llyods.assignment.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.llyods.assignment.R
import com.llyods.assignment.databinding.FragmentTopArtistBinding
import com.llyods.assignment.domain.model.TopArtist
import com.llyods.assignment.presentation.viewmodel.TopArtistViewModel
import com.llyods.assignment.presentation.viewmodel.ViewState
import com.llyods.assignment.presentation.views.adapter.ItemClickListener
import com.llyods.assignment.presentation.views.adapter.TopArtistAdapter
import com.llyods.assignment.showToast
import com.llyods.assignment.utility.Constant
import com.llyods.assignment.utility.EspressoIdlingResource
import com.llyods.assignment.utility.ItemSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TopArtistFragment : Fragment() {

    private lateinit var binding: FragmentTopArtistBinding
    private val viewModel: TopArtistViewModel by viewModels()

    @Inject
    lateinit var topArtistAdapter: TopArtistAdapter


    private val topArtistItemClickListener: ItemClickListener<TopArtist> = { artist ->
        findNavController().navigate(
            R.id.action_topArtistFragment_to_artistDetailFragment,
            Bundle().apply {
                putString(Constant.ARTIST_URL, artist.url)
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
        with(binding){
            rvArtists.addItemDecoration(ItemSpaceDecoration(
                resources.getDimension(R.dimen.default_padding).toInt()))
            lifecycleScope.launchWhenStarted {
                viewModel.getTopArtists().collect { viewState ->
                    when (viewState) {
                        is ViewState.Loading -> {
                            //Espresso Idling
                            EspressoIdlingResource.increment()
                            progressBar.visibility =
                                if (viewState.isLoading) View.VISIBLE else View.GONE
                        }
                        is ViewState.Failure -> {
                            viewState.throwable.message?.let {
                                context?.showToast(it)
                            }
                        }
                        is ViewState.Success -> {
                            val data = viewState.output

                            with(topArtistAdapter){
                                rvArtists.adapter = this
                                listner = topArtistItemClickListener
                                itemList = data
                            }

                            //Espresso Idling
                            EspressoIdlingResource.decrement()
                        }
                    }
                }
            }
        }
        viewModel.fetchTopArists()
    }


}