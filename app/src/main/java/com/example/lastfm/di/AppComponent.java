package com.example.lastfm.di;

import com.example.lastfm.data.RemoteDataSource;
import dagger.Component;
import javax.inject.Singleton;


@Singleton
@Component(modules = {NetworkModule.class})


public interface AppComponent{
    void inject (RemoteDataSource remoteDataSource);
}