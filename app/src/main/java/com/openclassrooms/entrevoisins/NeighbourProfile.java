package com.openclassrooms.entrevoisins;

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

import com.bumptech.glide.Glide;
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
    private List<Neighbour> mNeighbours;
    private Neighbour mNeighbour;
    private Integer mId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);


        mAvatar = findViewById(R.id.avatar);
        mAvatarName = findViewById(R.id.item_list_avatarName);
        mBack_button = findViewById(R.id.back_button);
        mFavorite = findViewById(R.id.favorite);
        mNeighbourDetailName = findViewById(R.id.neighbourDetailName);
        mNeighbourPlace = findViewById(R.id.neighbourPlace);
        mPhone = findViewById(R.id.phone);
        mWebsite = findViewById(R.id.website);
        mAboutMeLyt = findViewById(R.id.aboutMeLyt);



       // long id = getIntent().getLongExtra("id",0);
        // mApiService = DI.getNeighbourApiService();
        // mNeighbours = mApiService.getNeighbours();

        long id = getIntent().getLongExtra("id",0);
         mApiService = DI.getNeighbourApiService();
         mNeighbours = mApiService.getNeighbours();


        for (Neighbour n : mNeighbours) {
            if (n.getId() == id)
                mNeighbour = n;
        }


        Log.i("id",mNeighbour.toString());


        Glide.with(mAvatar.getContext())
                .load(mNeighbour.getAvatarUrl())
                .centerCrop()
                .into(mAvatar);

        this.mAvatarName.setText(mNeighbour.getName());
        this.mNeighbourPlace.setText(mNeighbour.getAddress());
        this.mAboutMeLyt.setText(mNeighbour.getAboutMe());
        this.mPhone.setText(mNeighbour.getPhoneNumber());
        this.mNeighbourDetailName.setText(mNeighbour.getName());
        this.mWebsite.setText("www.facebook.fr/"+mNeighbour.getName());


    // Set FloatingActionButton
    mFavorite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(!mApiService.getFavorites().contains(mNeighbour)){
                mApiService.addFavorite(mNeighbour);
            }

            else mApiService.deleteFavorite(mNeighbour);
        }


    });


    // Set BackButton
    mBack_button.setOnClickListener(v -> startActivity(new Intent
            (NeighbourProfile.this, ListNeighbourActivity.class)));

    // Set FragmentFavorite


    }

}