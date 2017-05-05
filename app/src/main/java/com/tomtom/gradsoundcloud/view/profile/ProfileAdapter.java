package com.tomtom.gradsoundcloud.view.profile;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tomtom.gradsoundcloud.R;
import com.tomtom.gradsoundcloud.domain.profile.model.User;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * ProfileAdapter
 * An Adapter that defines the UI for the Profile/User layout
 * It makes use of the RecyclerView Adapter & contains UserHolder static class
 * @see android.support.v7.widget.RecyclerView.Adapter
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.UserHolder> {

    private List<User> users;
    private WeakReference<ProfileFragment> fragmentWeakReference;

    /**
     * Instantiates a new Profile adapter.
     *
     * @param fragment the fragment
     * @param users    the users
     */
    public ProfileAdapter(ProfileFragment fragment, List<User> users) {
        fragmentWeakReference = new WeakReference<ProfileFragment>(fragment);
        this.users = users;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_profile_row, parent, false);
        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, final int i) {
        Picasso.with(this.fragmentWeakReference.get().getContext()).load(users.get(i).getAvatarUrl()).placeholder(R.drawable.progress_animation).into(holder.profileImage);
        holder.name.setText(users.get(i).getFullNameDescription());
        holder.city.setText(users.get(i).getUserCityDescription());
        holder.followersCount.setText(String.valueOf(users.get(i).getFollowersCountDescription()));
        holder.playListCount.setText(String.valueOf(users.get(i).getPlayListCountDescription()));
        holder.onlineStatus.setText(users.get(i).getOnlineStatus());
        holder.username.setText(users.get(i).getUsernameDescription());

        holder.removeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentWeakReference.get().removeSelectedUser();
            }
        });

        holder.viewTracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentWeakReference.get().gotoProfileTracks(String.valueOf(users.get(i).getId()));
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    /**
     * Update data.
     *
     * @param user the user
     */
    public void updateData(User user) {
        users = new ArrayList<>();
        users.add(user);
        notifyDataSetChanged();
    }

    /**
     * View holder class that is used to hold the components of each list item in the ProfileAdapter & Recyclerview it belongs to
     * @see android.support.v7.widget.RecyclerView.ViewHolder
     * @see ProfileAdapter
     */
    public static class UserHolder extends RecyclerView.ViewHolder {
        /**
         * The Card view.
         */
        CardView cardView;
        /**
         * The Profile image.
         */
        ImageView profileImage;
        /**
         * The Name.
         */
        TextView name;
        /**
         * The City.
         */
        TextView city;
        /**
         * The Followers count.
         */
        TextView followersCount;
        /**
         * The Play list count.
         */
        TextView playListCount;
        /**
         * The Online status.
         */
        TextView onlineStatus;
        /**
         * The Username.
         */
        TextView username;

        /**
         * The Remove user.
         */
        TextView removeUser;
        /**
         * The View tracks.
         */
        TextView viewTracks;

        /**
         * Instantiates a new User holder.
         *
         * @param itemView the item view
         */
        public UserHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            profileImage = (ImageView) itemView.findViewById(R.id.profile_avatar);
            name = (TextView) itemView.findViewById(R.id.user_name);
            city = (TextView) itemView.findViewById(R.id.user_city);
            followersCount = (TextView) itemView.findViewById(R.id.user_followers);
            playListCount = (TextView) itemView.findViewById(R.id.user_tracks);
            onlineStatus = (TextView) itemView.findViewById(R.id.online_status);
            removeUser = (TextView) itemView.findViewById(R.id.remove);
            viewTracks = (TextView) itemView.findViewById(R.id.tracks);
            username = (TextView) itemView.findViewById(R.id.user_username);
        }
    }
}
