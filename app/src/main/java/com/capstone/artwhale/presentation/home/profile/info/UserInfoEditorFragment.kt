package com.capstone.artwhale.presentation.home.profile.info

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentUserInfoEditorBinding
import com.capstone.artwhale.presentation.home.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class UserInfoEditorFragment : Fragment() {

    private var _binding: FragmentUserInfoEditorBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<UserViewModel>()

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            try {
                uri?.apply { viewModel.setProfileImage(uri) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserInfoEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButton()
        initObserver()
    }

    private fun initObserver() {
        viewModel.myInfo.onEach {
            if (_binding != null) binding.viewModel = viewModel
        }.launchIn(this.lifecycleScope)
    }

    private fun initButton() {
        with(binding) {
            btnSave.setOnClickListener { findNavController().navigate(R.id.action_profile_save) }
            ivProfileImg.setOnClickListener { getContent.launch("image/*") }
            btnEditImage.setOnClickListener { getContent.launch("image/*") }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
