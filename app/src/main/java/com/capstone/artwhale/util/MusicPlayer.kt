package com.capstone.artwhale.util

import android.util.Log

class MusicPlayer : Thread() {

    override fun run() {
        super.run()
        try {
            Log.d("Tester", "run: running")
        } catch (e: Exception) {
            Log.e("Tester", "run: $e")
        }
    }
}