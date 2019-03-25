package com.example.lastfm.home;

import com.example.lastfm.data.albumDetailed.Album;
import com.example.lastfm.data.albumDetailed.Wiki;
import com.example.lastfm.data.albumSearch.Albummatches;
import com.example.lastfm.data.albumSearch.Results;

import java.util.List;

public interface HomeContract {
    interface Presenter {
        void loadAllAlbums(String albumName, String apiKey, String format, String method);
        void onAlbumSelected(Album album);
    }

        interface View{
           // void showAllAlbums(List<Album> albums);
            void showDetailedAlbum(String name, String artist, Wiki wiki);
            void showError(String message);

            void showAllAlbums(List<com.example.lastfm.data.albumSearch.Album> album);
        }
}
