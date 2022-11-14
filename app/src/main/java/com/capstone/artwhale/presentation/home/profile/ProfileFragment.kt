package com.capstone.artwhale.presentation.home.profile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigation()
        initBlurView()
        initObserver()
    }

    private fun initObserver() {
        this.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                with(viewModel) {
                    launch { myInfo.collect { } }
                    launch { myAlbum.collect { } }
                    launch { likeAlbum.collect { } }
                    launch { myMusic.collect { } }
                    launch { likeMusic.collect { } }
                }
            }
        }
    }

    private fun initBlurView() {
        Blurry.with(this.requireContext())
            .radius(10)
            .sampling(8)
            .async()
            .color(Color.argb(66, 255, 255, 0))
            .animate(500)
            .capture(binding.clRoot)
            .into(binding.ivBlur)
    }

    private fun initNavigation() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fcv_profile)

        val navController = (navHostFragment as NavHostFragment).navController
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}