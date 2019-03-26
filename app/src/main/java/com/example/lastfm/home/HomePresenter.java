package com.example.lastfm.home;

import com.example.lastfm.data.DataSource;
import com.example.lastfm.data.RemoteDataSource;
import com.example.lastfm.data.albumDetailed.Album;

import com.example.lastfm.data.albumSearch.Albummatches;


import java.util.List;



public class HomePresenter implements HomeContract.Presenter, DataSource.DataListener {

    private final HomeContract.View view;
    private final DataSource dataSource;
    String mbid;



    public HomePresenter(HomeContract.View view) {
        this.view = view;
        dataSource = new RemoteDataSource(this);


    }

    @Override
    public void onAlbumsRetrieved(Albummatches albummatches) {
        view.showAllAlbums(albummatches.getAlbum());
    }

    @Override
    public void onError(Throwable throwable) {
        view.showError(throwable.getMessage());
    }

    @Override
    public void onAlbumDetailRetrieved(Album album) {
        view.showDetailedAlbum(album.getListeners(), album.getPlaycount(), (List<Album>) album.getTracks());
    }

    @Override
    public void loadAllAlbums(String albumName, String apiKey, String format, String method) {
        dataSource.getListOfAlbums(albumName, apiKey, format, method);
    }

    @Override
    public void onAlbumSelected(String mbid, String albumName, String apiKey, String format, String method) {
        dataSource.getAlbumInfo(mbid, albumName, apiKey, format, method);
    }


}
