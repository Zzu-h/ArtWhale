package com.capstone.artwhale.presentation.register.music.upload

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentMusicUploadBinding
import com.capstone.artwhale.domain.model.Mood
import com.capstone.artwhale.presentation.common.BaseFragment
import com.capstone.artwhale.presentation.register.music.MusicRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MusicUploadFragment :
    BaseFragment<FragmentMusicUploadBinding>(FragmentMusicUploadBinding::inflate) {

    private val viewModel by activityViewModels<MusicRegisterViewModel>()

    override fun initAfterBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initObserver()
        initButton()
    }

    private fun initSpinner(list: List<Mood>) {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            list.map { it.name })
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) =
                viewModel.selectMood(pos)

            override fun onNothingSelected(parent: AdapterView<*>) = Unit
        }
    }

    private fun initButton() {
        with(binding) {
            btnRegister.setOnClickListener {
                if (viewModel!!.isAI.value) graphNavigate(R.id.action_to_ai_album_select)
                else requireActivity().finish()
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.isAI.collect {
                        val text = if (it) "Run" else "Register"
                        binding.btnRegister.text = text
                    }
                }
                launch { viewModel.moodList.collect { initSpinner(it) } }
                launch {
                    viewModel.musicUri.collect {
                        binding.btnRegister.isEnabled = it != null
                    }
                }
            }
        }
        viewModel.isAI.onEach {
            val text = if (it) "Run" else "Register"
            binding.btnRegister.text = text
        }.launchIn(this.lifecycleScope)
    }
}