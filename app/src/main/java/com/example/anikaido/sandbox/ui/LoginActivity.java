package com.example.anikaido.sandbox.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.anikaido.sandbox.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;

    @BindView(R.id.login_button)
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();

        setupLoginButton();
    }

    private void setupLoginButton() {
        final Activity activity = this;

        loginButton.setReadPermissions("public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(activity, HomeActivity.class);

                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {
                System.out.print("hogehoge");
            }

            @Override
            public void onError(FacebookException error) {
                System.out.print("hogehoge");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
