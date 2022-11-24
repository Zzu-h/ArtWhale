package com.capstone.artwhale.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.capstone.artwhale.R
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.presentation.home.play.MusicPlayerFragment

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAfterBinding()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun initAfterBinding()

    protected fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun playMusic(music: Music) {
        val player = MusicPlayerFragment()
        val bundle = Bundle()
        bundle.putSerializable("music", music)
        player.arguments = bundle
        player.show(childFragmentManager, getString(R.string.fragment_music_player))
    }

    protected fun graphNavigate(actionId: Int) {
        findNavController().navigate(actionId)
    }

    protected fun isBindingNull(): Boolean = _binding == null
}