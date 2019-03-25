package com.example.lastfm.home;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.lastfm.R;
import com.example.lastfm.data.albumSearch.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {
    private final List<Album> results;
    private final OnItemSelectedListener listener;

    public AlbumsAdapter(OnItemSelectedListener listener) {
        results = new ArrayList<>();
        this.listener = listener;
    }

    public void setData(List<Album> data) {
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int parent) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.album_list_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(results.get(position).getName());
        holder.artist.setText(results.get(position).getArtist());
        holder.url.setText(results.get(position).getUrl());
        holder.streamable.setText(results.get(position).getStreamable());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemSelected(results.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView artist;
        final TextView url;
        final TextView streamable;

        ViewHolder(View view) {
            super(view);
            name =  view.findViewById(R.id.tvName);
            artist =  view.findViewById(R.id.tvArtist);
            url =  view.findViewById(R.id.tvURL);
            streamable =  view.findViewById(R.id.tvStreamable);

        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(Album album);
    }

}
