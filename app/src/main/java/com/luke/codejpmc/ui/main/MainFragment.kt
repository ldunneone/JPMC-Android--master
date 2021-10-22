package com.luke.codejpmc.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luke.codejpmc.R
import com.luke.codejpmc.database.DatabaseAlbum
import com.luke.codejpmc.databinding.MainFragmentBinding
import com.luke.codejpmc.utils.LoadingState
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private  lateinit var viewModelAdapter: AlbumsAdapter
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MainFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.main_fragment,container,false)
        binding.setLifecycleOwner (viewLifecycleOwner)
        binding.viewmodel = viewModel
        viewModelAdapter = AlbumsAdapter() {
            navigateToAlbumDetails(album = it)
        }

        binding.root.findViewById<RecyclerView>(R.id.rvAlbums).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()

    }

    private fun navigateToAlbumDetails(album:DatabaseAlbum) {
        findNavController().navigate(R.id.action_mainFragment_to_albumDetailFragment,
            Bundle().apply { putInt("albumId",album.id) })
    }

    private fun setUpObserver() {
        viewModel.albumsResults.observe(viewLifecycleOwner, Observer<List<DatabaseAlbum>> { album ->
            album?.apply {
                viewModelAdapter?.result = album
            }
        })

        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> {
                    Toast.makeText(activity, it.msg, Toast.LENGTH_SHORT).show()
                }
                LoadingState.Status.RUNNING -> {

                }
                LoadingState.Status.SUCCESS -> {

                }
            }
        })
    }
}