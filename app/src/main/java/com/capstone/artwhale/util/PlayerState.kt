package com.capstone.artwhale.util

sealed class PlayerState

object Playing : PlayerState()
object Pause : PlayerState()
class Error(val msg: String? = null) : PlayerState()