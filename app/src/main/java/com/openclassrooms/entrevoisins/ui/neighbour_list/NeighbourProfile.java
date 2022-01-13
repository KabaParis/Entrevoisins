package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourProfile extends AppCompatActivity {


    private ImageView mAvatar;
    private TextView mAvatarName;
    private ImageButton mBack_button;
    private FloatingActionButton mFavorite;
    private TextView mNeighbourDetailName;
    private TextView mNeighbourPlace;
    private TextView mPhone;
    private TextView mWebsite;
    private TextView mAboutMeLyt;

    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);

        // get the views identified by their attributes

        mAvatar = findViewById(R.id.avatar);
        mAvatarName = findViewById(R.id.item_list_avatarName);
        mBack_button = findViewById(R.id.back_button);
        mFavorite = findViewById(R.id.favorite);
        mNeighbourDetailName = findViewById(R.id.neighbourDetailName);
        mNeighbourPlace = findViewById(R.id.neighbourPlace);
        mPhone = findViewById(R.id.phone);
        mWebsite = findViewById(R.id.website);
        mAboutMeLyt = findViewById(R.id.aboutMeLyt);

        // get neighbour id by the api service

        long id = getIntent().getLongExtra("id", 0);
        mApiService = DI.getNeighbourApiService();
        mNeighbour = mApiService.getNeighboursById(id);

        //get attributes of widgets

        Glide.with(mAvatar.getContext())
                .load(mNeighbour.getAvatarUrl())
                .centerCrop()
                .into(mAvatar);

        this.mAvatarName.setText(mNeighbour.getName());
        this.mNeighbourPlace.setText(mNeighbour.getAddress());
        this.mAboutMeLyt.setText(mNeighbour.getAboutMe());
        this.mPhone.setText(mNeighbour.getPhoneNumber());
        this.mNeighbourDetailName.setText(mNeighbour.getName());
        this.mWebsite.setText("www.facebook.fr/" + mNeighbour.getName());

        // call
         setFavorite();

        // Set Favorite FloatingActionButton
        mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFavorite.setActivated(!mFavorite.isActivated());
                if (mFavorite.isActivated()) {
                    mApiService.addFavorite(mNeighbour);
                    mFavorite.setImageResource(R.drawable.ic_star_yellow_24);

                } else {
                    mApiService.deleteFavorite(mNeighbour);
                    mFavorite.setImageResource(R.drawable.ic_star_border_yellow_24);

                }

            }

            });

                // Set BackButton
                mBack_button.setOnClickListener(v -> finish());

            }
    // call method boolean DummyApi
    public void setFavorite(){
        if (mApiService.setFavorite(mNeighbour) == true) {
            mFavorite.setActivated(true);
            mFavorite.setImageResource(R.drawable.ic_star_yellow_24);
        } else {
            mFavorite.setImageResource(R.drawable.ic_star_border_yellow_24);
        }

    }

        }
