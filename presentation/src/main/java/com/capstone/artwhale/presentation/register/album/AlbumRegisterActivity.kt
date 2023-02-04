package com.capstone.artwhale.presentation.register.album

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.databinding.ActivityAlbumRegisterBinding
import com.capstone.artwhale.domain.model.Mood
import com.capstone.artwhale.presentation.common.Error
import com.capstone.artwhale.presentation.common.Loading
import com.capstone.artwhale.presentation.common.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AlbumRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumRegisterBinding
    private val viewModel by viewModels<AlbumRegisterViewModel>()

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            try {
                uri?.apply { viewModel.setAlbumImage(uri) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityAlbumRegisterBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initButton()
        initObserver()
    }

    private fun initObserver() {
        viewModel.moodList.onEach {
            initSpinner(it)
            if (it.isNotEmpty()) viewModel.selectMood(0)
        }.launchIn(this.lifecycleScope)
        viewModel.state.onEach {
            when (it) {
                is Success -> {
                    Toast.makeText(this, "등록했습니다!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                is Loading -> {}
                is Error -> Toast.makeText(this, "${it.msg}", Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }.launchIn(this.lifecycleScope)
    }

    private fun initSpinner(list: List<Mood>) {
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list.map { it.name })
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) =
                viewModel.selectMood(pos)

            override fun onNothingSelected(parent: AdapterView<*>) = Unit
        }
    }

    private fun initButton() {
        with(binding) {
            ivAlbumThumbnail.setOnClickListener { getContent.launch("image/*") }
            cvRegister.setOnClickListener { viewModel?.onClickRegister() }
            ctbSub.setOnClickDefaultIcon { finish() }
        }
    }
}