package com.example.vinilos_rabbits.utils

import androidx.annotation.StringRes
import com.example.vinilos_rabbits.R

enum class VinilosScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    AlbumDetail(title = R.string.album_detail_navbar_title),
}