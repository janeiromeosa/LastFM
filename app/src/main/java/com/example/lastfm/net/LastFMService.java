package com.example.lastfm.net;

import com.example.lastfm.data.albumDetailed.Album;
import com.example.lastfm.data.albumDetailed.LastFMAlbumDetailed;
import com.example.lastfm.data.albumSearch.Albummatches;
import com.example.lastfm.data.albumSearch.LastFMAlbumSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFMService {
    @GET(Constants.TOP_ENDPOINT)
    Call<Albummatches> searchForAlbums(@Query("album")String apiKey,
                                      @Query("api_key") String album,
                                      @Query("format") String format,
                                      @Query("method") String method);

    Call <Album> searchForDetailedAlbum(@Query("mbid")String mbid,
                                        @Query("album")String apiKey,
                                        @Query("api_key") String album,
                                        @Query("format") String format,
                                        @Query("method") String method);





}
