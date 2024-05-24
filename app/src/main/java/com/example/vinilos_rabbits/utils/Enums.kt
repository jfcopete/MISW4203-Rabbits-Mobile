package com.example.vinilos_rabbits.utils

import androidx.annotation.StringRes
import com.example.vinilos_rabbits.R

enum class VinilosScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    AlbumDetail(title = R.string.album_detail_navbar_title),
    Artist(title = R.string.artist),
    ArtistDetail(title = R.string.artist_detail_navbar_title),
    Collector(title = R.string.collector),
    CollectorDetail(title = R.string.artist_detail_navbar_title),;

}

enum class VinilosCategory(@StringRes val title: Int){
    Album(title = R.string.album),
    Artist(title = R.string.artist),
}

