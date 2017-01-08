package com.example.anikaido.sandbox.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anikaido.sandbox.R;
import com.example.anikaido.sandbox.ui.adapter.HomeRecyclerViewAdapter;
import com.example.anikaido.sandbox.ui.helper.HomeActivityHelper;
import com.facebook.AccessToken;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.functions.Action1;

/**
 * Created by anikaido on 2016/12/31.
 */

public class HomeActivity extends AppCompatActivity {
//    @BindView(R.id.accessTokenTextView)
//    TextView mAccessTokenTextView;

    @BindView(R.id.profile_image)
    CircleImageView mCircleImageView;

    @BindView(R.id.nameTextView)
    TextView mNameTextView;

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.home_drawer)
    DrawerLayout mDrawerLayout;

    AccessToken mAccessToken;

    HomeActivityHelper mHomeActivityHelper;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        // ツールバーをアクションバーとして使う
        mToolbar.setTitle("ホーム");
        setSupportActionBar(mToolbar);

        Intent intent = getIntent();
        mAccessToken = (AccessToken) intent.getParcelableExtra("accesstoken");
//        mAccessTokenTextView.setText(mAccessToken.getToken());

        mHomeActivityHelper = new HomeActivityHelper();

        setupRecyclerViewContent();
        setProfile();


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * 仮で詰めとく
     */
    private void setupRecyclerViewContent() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ArrayList<String> data = new ArrayList<>();
        data.add("A");
        data.add("B");
        data.add("C");
        data.add("D");
        data.add("E");
        data.add("F");
        data.add("G");

        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(this, data);
        mRecyclerView.setAdapter(adapter);
    }

    private void setProfile() {
        final Activity activity = this;
        mHomeActivityHelper.getProfile(mAccessToken).subscribe(new Action1<GraphResponse>() {
            @Override
            public void call(GraphResponse graphResponse) {
//                mAccessTokenTextView.setText(graphResponse.getRawResponse());

                try {
                    JSONObject json = new JSONObject(graphResponse.getRawResponse());
                    String url = json.getJSONObject("picture").getJSONObject("data").getString("url");
                    Glide.with(activity)
                            .load(url)
                            .fitCenter()
                            .into(mCircleImageView);
                    String name = json.getString("name");
                    mNameTextView.setText(name);
                } catch (Exception e) {
                    Log.d("hoge", e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
        }
        return true;
    }
}
