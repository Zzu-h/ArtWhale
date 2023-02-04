package com.capstone.artwhale.presentation.common

sealed class NetworkState

object InitialState : NetworkState()
object Success : NetworkState()
object Loading : NetworkState()
class Error(val msg: String? = null) : NetworkState()