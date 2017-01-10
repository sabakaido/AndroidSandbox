package com.example.anikaido.sandbox.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.anikaido.sandbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anikaido on 2017/01/10.
 */

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        mToolbar.setTitle("詳細");
    }
}
