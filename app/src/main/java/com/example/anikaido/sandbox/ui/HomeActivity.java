package com.example.anikaido.sandbox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.anikaido.sandbox.R;
import com.example.anikaido.sandbox.ui.helper.HomeActivityHelper;
import com.facebook.AccessToken;
import com.facebook.GraphResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by anikaido on 2016/12/31.
 */

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.accessTokenTextView)
    TextView accessTokenTextView;

    AccessToken mAccessToken;

    HomeActivityHelper mHomeActivityHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandbox_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mAccessToken = (AccessToken) intent.getParcelableExtra("accesstoken");
        accessTokenTextView.setText(mAccessToken.getToken());

        mHomeActivityHelper = new HomeActivityHelper();
        mHomeActivityHelper.getProfile(mAccessToken).subscribe(new Action1<GraphResponse>() {
            @Override
            public void call(GraphResponse graphResponse) {
                accessTokenTextView.setText(graphResponse.toString());
            }
        });
    }
}
