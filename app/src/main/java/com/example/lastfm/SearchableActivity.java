package com.example.lastfm;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.lastfm.data.albumDetailed.Album;
import com.example.lastfm.data.albumDetailed.Wiki;
import com.example.lastfm.home.AlbumsAdapter;
import com.example.lastfm.home.HomeContract;
import com.example.lastfm.home.HomePresenter;

import java.util.List;

public class SearchableActivity extends AppCompatActivity implements HomeContract.View, AlbumsAdapter.OnItemSelectedListener {

    private HomeContract.Presenter presenter;
    private final AlbumsAdapter adapter = new AlbumsAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_list_view);

        View recyclerView = findViewById(R.id.rvAlbums);


        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        presenter = new HomePresenter(this);
        presenter.loadAllAlbums();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showDetailedAlbum(String name, String artist, Wiki wiki) {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void showAllAlbums(List<com.example.lastfm.data.albumSearch.Album> album) {

    }

    @Override
    public void onItemSelected(com.example.lastfm.data.albumSearch.Album album) {
        presenter.onAlbumSelected(album);

    }
}
