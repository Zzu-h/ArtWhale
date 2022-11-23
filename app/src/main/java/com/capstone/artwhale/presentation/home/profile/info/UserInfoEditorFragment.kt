package com.capstone.artwhale.presentation.home.profile.info

import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentUserInfoEditorBinding
import com.capstone.artwhale.presentation.home.BaseFragment
import com.capstone.artwhale.presentation.home.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class UserInfoEditorFragment :
    BaseFragment<FragmentUserInfoEditorBinding>(FragmentUserInfoEditorBinding::inflate) {

    private val viewModel by activityViewModels<UserViewModel>()

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            try {
                uri?.apply { viewModel.setProfileImage(uri) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    override fun initAfterBinding() {
        initButton()
        initObserver()
    }

    private fun initObserver() {
        viewModel.myInfo.onEach {
            if (isBindingNull()) binding.viewModel = viewModel
        }.launchIn(this.lifecycleScope)
    }

    private fun initButton() {
        with(binding) {
            btnSave.setOnClickListener { graphNavigate(R.id.action_profile_save) }
            ivProfileImg.setOnClickListener { getContent.launch("image/*") }
            btnEditImage.setOnClickListener { getContent.launch("image/*") }
        }
    }
}
