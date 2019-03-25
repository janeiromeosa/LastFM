package com.example.lastfm.home;

import com.example.lastfm.data.DataSource;
import com.example.lastfm.data.RemoteDataSource;
import com.example.lastfm.data.albumDetailed.Album;
import com.example.lastfm.data.albumDetailed.LastFMAlbumDetailed;
import com.example.lastfm.data.albumSearch.Albummatches;
import com.example.lastfm.data.albumSearch.LastFMAlbumSearch;
import com.example.lastfm.data.albumSearch.Results;

import static com.example.lastfm.net.Constants.ALBUM_DETAILED_SEARCH;
import static com.example.lastfm.net.Constants.ALBUM_SEARCH;
import static com.example.lastfm.net.Constants.API_KEY;
import static com.example.lastfm.net.Constants.FORMAT;
import static javax.xml.transform.OutputKeys.METHOD;

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
        view.showDetailedAlbum(album.getName(), album.getArtist(),album.getWiki());
        mbid = album.getMbid();
    }

    @Override
    public void loadAllAlbums(String albumName, String apiKey, String format, String method) {
        dataSource.getListOfAlbums(albumName, API_KEY, FORMAT, ALBUM_SEARCH);
    }

    @Override
    public void onAlbumSelected(Album album) {
        dataSource.getAlbumInfo(mbid, album.getName(), API_KEY, FORMAT, ALBUM_DETAILED_SEARCH);
    }
}
