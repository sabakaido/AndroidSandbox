package com.example.anikaido.sandbox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.anikaido.sandbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anikaido on 2017/01/10.
 */

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @BindView(R.id.headerImage)
    ImageView mHeaderImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String transitionName = intent.getStringExtra("transitionName");

        mHeaderImage.setTransitionName(transitionName);

        mToolbar.setTitle("詳細");
    }
}
