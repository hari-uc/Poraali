package com.hari.poraali.SplashScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hari.poraali.Activities.LoginActivity;
import com.hari.poraali.Activities.MainActivity;
import com.hari.poraali.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_DELAY = 5000;
    private TextView textView;
    private ImageView splashImg;

    private final DatabaseReference imgReference = FirebaseDatabase.getInstance()
            .getReference()
            .child("img");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Install the splash screen (using fully qualified name to avoid conflict)
        androidx.core.splashscreen.SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        // Enable edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_splash_screen);

        splashImg = findViewById(R.id.splash_img);
        textView = findViewById(R.id.spashimageurl);

        // Setup fullscreen mode
        setupFullscreen();

        // Load splash image
        loadSplashImage();

        // Navigate after delay
        new Handler(Looper.getMainLooper()).postDelayed(this::navigateToNextScreen, SPLASH_DELAY);
    }

    private void setupFullscreen() {
        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        if (windowInsetsController != null) {
            windowInsetsController.setSystemBarsBehavior(
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            );
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        }
    }

    private void loadSplashImage() {
        if (isNetworkAvailable()) {
            imgReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String imageUrl = snapshot.getValue(String.class);
                    if (imageUrl != null) {
                        textView.setText(imageUrl);
                        Picasso.get()
                                .load(imageUrl)
                                .placeholder(R.drawable.poraalispash)
                                .error(R.drawable.poraalispash)
                                .into(splashImg);
                    } else {
                        splashImg.setImageResource(R.drawable.poraalispash);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    splashImg.setImageResource(R.drawable.poraalispash);
                }
            });
        } else {
            splashImg.setImageResource(R.drawable.poraalispash);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            return capabilities != null && (
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            );
        } else {
            android.net.NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
    }

    private void navigateToNextScreen() {
        boolean isLoggedIn = getSharedPreferences("login", MODE_PRIVATE)
                .getBoolean("isLoginKey", false);

        Intent intent = isLoggedIn
                ? new Intent(this, MainActivity.class)
                : new Intent(this, LoginActivity.class);

        startActivity(intent);
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setupFullscreen();
        }
    }
}