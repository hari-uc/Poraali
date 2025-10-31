package com.hari.poraali.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hari.poraali.R;

public class LoginActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "login";
    private static final String KEY_IS_LOGGED_IN = "isLoginKey";
    private static final String KEY_USER_NAME = "NAME";

    private EditText userText;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Modern edge-to-edge display
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_login);

        userText = findViewById(R.id.usernicknametxt);
        loginBtn = findViewById(R.id.loginbtn);

        // Check if user is already logged in
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String userName = prefs.getString(KEY_USER_NAME, null);

        if (userName != null) {
            navigateToMain();
            finish();
            return;
        }

        loginBtn.setOnClickListener(view -> {
            String inputText = userText.getText().toString().trim();

            if (inputText.isEmpty()) {
                Toast.makeText(this, "Please enter a nickname", Toast.LENGTH_SHORT).show();
            } else if (inputText.length() > 8) {
                Toast.makeText(this, "Please enter a name with max 8 characters", Toast.LENGTH_SHORT).show();
            } else {
                onLoginSuccess(inputText);
            }
        });
    }

    private void onLoginSuccess(String userName) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit()
                .putBoolean(KEY_IS_LOGGED_IN, true)
                .putString(KEY_USER_NAME, userName)
                .apply();

        navigateToMain();
        finish();
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}