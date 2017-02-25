package com.example.anikaido.sandbox.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.anikaido.sandbox.R;
import com.example.anikaido.sandbox.ui.helper.ProfileActivityHelper;
import com.facebook.AccessToken;
import com.facebook.GraphResponse;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by anikaido on 2017/02/01.
 */

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.profileCoverImage)
    ImageView mProfileCoverImage;

    ProfileActivityHelper mProfileActivityHelper;

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @BindView(R.id.profileAvatar)
    ImageView mAvatarImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        mProfileActivityHelper = new ProfileActivityHelper();

        mToolbar.setTitle("プロフィール");

        Intent intent = getIntent();
        String avatar = intent.getStringExtra("avatarUrl");

        final Activity activity = this;

        Glide.with(this)
                .load(avatar)
                .centerCrop()
                .into(mAvatarImage);

//        String transitionName = "avatar";
//        mAvatarImage.setTransitionName(transitionName);

        mProfileActivityHelper.getCover(AccessToken.getCurrentAccessToken()).subscribe(new Action1<GraphResponse>() {

            @Override
            public void call(GraphResponse graphResponse) {
                try {
                    JSONObject json = new JSONObject(graphResponse.getRawResponse());
                    String coverUrl = json.getJSONObject("cover").getString("source");
                    Glide.with(activity)
                            .load(coverUrl).fitCenter()
                            .into(mProfileCoverImage);
                } catch (Exception e) {
                    Log.d("hoge", e.getMessage());
                }
            }
        });
    }
}
