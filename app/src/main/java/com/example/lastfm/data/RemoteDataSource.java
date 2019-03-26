package com.example.lastfm.data;


import com.example.lastfm.data.albumDetailed.Album;
import com.example.lastfm.data.albumDetailed.LastFMAlbumDetailed;
import com.example.lastfm.data.albumSearch.Albummatches;
import com.example.lastfm.data.albumSearch.LastFMAlbumSearch;
import com.example.lastfm.net.Constants;
import com.example.lastfm.net.LastFMService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {

    private final DataSource.DataListener listener;
    private final LastFMService lastFMService;

    public RemoteDataSource(DataListener listener) {
        this.listener = listener;


        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(new HttpLoggingInterceptor().
                        setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lastFMService = retrofit.create(LastFMService.class);

    }

    @Override
    public void getListOfAlbums(String albumName, String apiKey, String format, String method) {
        lastFMService.searchForAlbums(albumName, apiKey, format, method).enqueue(new Callback<Albummatches>() {
            @Override
            public void onResponse(Call<Albummatches> call, Response<Albummatches> response) {
                if(response.isSuccessful()){
                    listener.onAlbumsRetrieved(response.body());
                }
            }

            @Override
            public void onFailure(Call<Albummatches> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void getAlbumInfo(String mbid, String albumName, String apiKey, String format, String method) {
        lastFMService.searchForDetailedAlbum(mbid, albumName, apiKey, format, method).enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                if (response.isSuccessful()){
                    listener.onAlbumDetailRetrieved(response.body());
                }
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                listener.onError(t);
            }
        });
    }




}
