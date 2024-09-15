package com.ahmed.amdbmoviesapp.activities

import android.app.Application
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import com.ahmed.amdbmoviesapp.R

class HomeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize EmojiCompat
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "emoji_bundled_fonts",
            R.array.com_google_android_gms_fonts_certs
        )
        val config = FontRequestEmojiCompatConfig(this, fontRequest)
        EmojiCompat.init(config)
    }
}