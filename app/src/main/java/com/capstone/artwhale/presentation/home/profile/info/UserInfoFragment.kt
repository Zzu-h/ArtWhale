package com.capstone.artwhale.presentation.home.profile.info

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentUserInfoBinding
import com.capstone.artwhale.presentation.common.BaseFragment
import com.capstone.artwhale.presentation.home.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>(FragmentUserInfoBinding::inflate) {

    private val viewModel by activityViewModels<UserViewModel>()

    override fun initAfterBinding() {
        initObserver()
        initButton()
    }

    private fun initButton() {
        binding.btnEdit.setOnClickListener {
            graphNavigate(R.id.action_profile_edit)
        }
    }

    private fun initObserver() {
        viewModel.myInfo.onEach {
            if (!isBindingNull()) binding.viewModel = viewModel
        }.launchIn(this@UserInfoFragment.lifecycleScope)
    }
}