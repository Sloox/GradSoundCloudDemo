package com.tomtom.gradsoundcloud.view.favourites;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tomtom.gradsoundcloud.R;
import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;
import com.tomtom.gradsoundcloud.view.widgets.LikeWidget;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * Favourites Adapter
 * An Adapter that defines the UI for the Favourites/Tracks layout
 * It makes use of the RecyclerView Adapter & contains TrackHolder static class
 * @see android.support.v7.widget.RecyclerView.Adapter
 * @see TrackHolder
 */
public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.TrackHolder> {

    private List<Tracks> tracksList;
    private final WeakReference<FavouritesFragment> fragmentWeakReference;


    /**
     * Instantiates a new Favourites adapter.
     *
     * @param fragment   the fragment
     * @param tracksList the tracks list
     */
    public FavouritesAdapter(FavouritesFragment fragment, List<Tracks> tracksList) {
        fragmentWeakReference = new WeakReference<>(fragment);
        this.tracksList = tracksList;
    }

    /**
     * Update data to reflect on the ui.
     *
     * @param tracksList the tracks list
     */
    public void updateData(List<Tracks> tracksList) {
        this.tracksList = new ArrayList<>();
        this.tracksList.addAll(tracksList);
        notifyDataSetChanged();
    }

    @Override
    public TrackHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_tracks_row, parent, false);
        return new TrackHolder(v);
    }

    @Override
    public void onBindViewHolder(TrackHolder holder, int position) {
        final Tracks track = tracksList.get(position);
        Picasso.with(this.fragmentWeakReference.get().getContext()).load(track.getTrackPicture()).placeholder(R.drawable.progress_animation).error(R.drawable.error).into(holder.trackPicture);
        holder.trackUserName.setText(track.getUser().getUsernameDescription());
        holder.trackName.setText(track.getTitleDescription());
        holder.trackDescription.setText(track.getDescriptionFormatted());

        holder.playbackCount.setText(String.valueOf(track.getPlaybackCount()));
        holder.playbackCount.setImageIcon(R.drawable.play);
        holder.playbackCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(track.getPermalinkUrl()));
                FavouritesAdapter.this.fragmentWeakReference.get().startActivity(i);
            }
        });

        holder.commentsCount.setText(String.valueOf(track.getCommentCount()));
        holder.commentsCount.setImageIcon(R.drawable.comment);
        holder.commentsCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(track.getPermalinkUrl()));
                FavouritesAdapter.this.fragmentWeakReference.get().startActivity(i);
            }
        });

        holder.favouritesCount.setText(String.valueOf(track.getFavoritingsCount()));
        holder.favouritesCount.setImageIcon(R.drawable.heart);
        holder.favouritesCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(track.getPermalinkUrl()));
                FavouritesAdapter.this.fragmentWeakReference.get().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return tracksList.size();
    }

    /**
     *  View holder class that is used to hold the components of each list item in the FavouritesAdapter & Recyclerview it belongs to
     *  @see FavouritesAdapter
     */
    public static class TrackHolder extends RecyclerView.ViewHolder {
        /**
         * The Card view.
         */
        CardView cardView;
        /**
         * The Track picture.
         */
        ImageView trackPicture;
        /**
         * The Track user name.
         */
        TextView trackUserName;
        /**
         * The Track name.
         */
        TextView trackName;
        /**
         * The Track description.
         */
        TextView trackDescription;
        /**
         * The Playback count.
         */
        LikeWidget playbackCount;
        /**
         * The Comments count.
         */
        LikeWidget commentsCount;
        /**
         * The Favourites count.
         */
        LikeWidget favouritesCount;

        /**
         * Instantiates a new Track holder.
         *
         * @param itemView the item view
         */
        public TrackHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            trackPicture = (ImageView) itemView.findViewById(R.id.track_picture);
            trackUserName = (TextView) itemView.findViewById(R.id.track_user_name);
            trackName = (TextView) itemView.findViewById(R.id.track_name);
            trackDescription = (TextView) itemView.findViewById(R.id.track_description);

            playbackCount = (LikeWidget) itemView.findViewById(R.id.playback_count);
            commentsCount = (LikeWidget) itemView.findViewById(R.id.comments_count);
            favouritesCount = (LikeWidget) itemView.findViewById(R.id.favourites_count);

        }

    }
}