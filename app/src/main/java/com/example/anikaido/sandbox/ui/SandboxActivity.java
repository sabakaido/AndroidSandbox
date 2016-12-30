package com.example.anikaido.sandbox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.anikaido.sandbox.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SandboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandbox);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.sandboxButton)
    void goDetail() {
        Intent intent = new Intent(this, SandboxDetailActivity.class);
        startActivity(intent);
    }

}
