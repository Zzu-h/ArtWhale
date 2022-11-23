package com.capstone.artwhale.presentation.home.profile.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentUserInfoBinding
import com.capstone.artwhale.presentation.home.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initButton()
    }

    private fun initButton() {
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_profile_edit)
        }
    }

    private fun initObserver() {
        viewModel.myInfo.onEach {
            if (_binding != null) binding.viewModel = viewModel
        }.launchIn(this@UserInfoFragment.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}