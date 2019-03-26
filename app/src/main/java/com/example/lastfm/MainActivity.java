package com.example.lastfm;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastfm.data.albumDetailed.Wiki;
import com.example.lastfm.data.albumSearch.Album;
import com.example.lastfm.home.AlbumsAdapter;
import com.example.lastfm.home.HomeContract;
import com.example.lastfm.home.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import static com.example.lastfm.net.Constants.ALBUM_DETAILED_SEARCH;
import static com.example.lastfm.net.Constants.ALBUM_SEARCH;
import static com.example.lastfm.net.Constants.API_KEY;
import static com.example.lastfm.net.Constants.FORMAT;

public class MainActivity extends AppCompatActivity implements HomeContract.View, AlbumsAdapter.OnItemSelectedListener {

    private final AlbumsAdapter albumsAdapter = new AlbumsAdapter(this);

    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View recyclerView = findViewById(R.id.rvAlbums);


        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        presenter = new HomePresenter(this);
        presenter.loadAllAlbums("char" ,API_KEY,FORMAT,ALBUM_SEARCH);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(albumsAdapter);
    }


    @Override
    public void showDetailedAlbum(String listeners, String playCount, List<com.example.lastfm.data.albumDetailed.Album> tracks) {
        Intent intent = new Intent(String.valueOf(this));
        //intent.putExtra(ItemDetailFragment.PLANET_NAME, name);
       // intent.putStringArrayListExtra(ItemDetailFragment.FILMS_LIST, (ArrayList)films);
       // startActivity(intent);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showAllAlbums(List<Album> album) {
        albumsAdapter.setData(album);
    }

    @Override
    public void onItemSelected(Album album) {
        presenter.onAlbumSelected(album.getMbid(),album.getName(), API_KEY, FORMAT, ALBUM_DETAILED_SEARCH);

    }
}

