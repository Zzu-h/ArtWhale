package com.capstone.artwhale.presentation.home.profile.common

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.databinding.FragmentLikeAlbumBinding
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.presentation.home.UserViewModel
import com.capstone.artwhale.presentation.home.album.adapter.AlbumRVAdapter
import com.capstone.artwhale.presentation.register.music.MusicRegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AlbumListFragment : Fragment() {

    private var _binding: FragmentLikeAlbumBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvAdapter: AlbumRVAdapter
    private val viewModel by activityViewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLikeAlbumBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.likeAlbum.onEach {
            rvAdapter.submitList(it)
        }.launchIn(this.lifecycleScope)
    }

    private fun registerMusicWithAlbum(album: Album) {
        val intent = Intent(requireContext(), MusicRegisterActivity::class.java)
        intent.putExtra("album", album)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        with(binding) {
            rvAdapter = AlbumRVAdapter().apply {
                setCallBack(
                    { registerMusicWithAlbum(it) },
                    { viewModel.updateAlbumLikeState(it) })
            }
            rvLikeAlbum.adapter = rvAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}