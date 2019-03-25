package com.example.lastfm.data;

import com.example.lastfm.data.albumDetailed.Album;
import com.example.lastfm.data.albumSearch.Albummatches;


public interface DataSource {
    //getter interface for albums
    void getListOfAlbums(String albumName, String apiKey, String format, String method);
    void getAlbumInfo(String mbid, String albumName, String apiKey, String format, String method);

    interface DataListener {
        void onAlbumsRetrieved(Albummatches albummatches);
        void onError(Throwable throwable);
        void onAlbumDetailRetrieved(Album album);

    }
}
