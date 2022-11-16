package com.capstone.artwhale.presentation.home.profile.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentUserInfoEditorBinding
import com.capstone.artwhale.presentation.home.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoEditorFragment : Fragment() {

    private var _binding: FragmentUserInfoEditorBinding? = null
    private val binding get() = _binding!!

    private val viewModel by hiltNavGraphViewModels<ProfileViewModel>(R.id.nav_profile_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserInfoEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_profile_save)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
