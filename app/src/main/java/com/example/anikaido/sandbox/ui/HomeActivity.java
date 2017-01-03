package com.example.anikaido.sandbox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.anikaido.sandbox.R;
import com.facebook.AccessToken;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anikaido on 2016/12/31.
 */

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.accessTokenTextView)
    TextView accessTokenTextView;

    AccessToken mAccessToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandbox_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mAccessToken = (AccessToken) intent.getParcelableExtra("accesstoken");

        accessTokenTextView.setText(mAccessToken.getToken());
    }
}
