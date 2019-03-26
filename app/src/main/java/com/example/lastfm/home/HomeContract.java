package com.example.lastfm.home;

import com.example.lastfm.data.albumDetailed.LastFMAlbumDetailed;

import com.example.lastfm.data.albumSearch.Album;


import java.util.List;

public interface HomeContract {
    interface Presenter {
        void loadAllAlbums(String albumName, String apiKey, String format, String method);
        void onAlbumSelected(String mbid, String albumName, String apiKey, String format, String method);
    }

        interface View{
           // void showAllAlbums(List<Album> albums);
            void showDetailedAlbum(String listeners, String playCount, List<com.example.lastfm.data.albumDetailed.Album> albums);
            void showAllAlbums(List<Album> albumList);
            void showError(String message);


        }
}
