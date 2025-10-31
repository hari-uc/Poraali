package com.hari.poraali.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.hari.poraali.QuotesActivity.QuotesActivity;
import com.hari.poraali.R;
import com.hari.poraali.ToDoFiles.TodoActivity;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    TextView textView;
    LottieAnimationView lottienews, lottietodo, lottienotes, lottiequotes;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        textView = findViewById(R.id.textnickname);

        lottienews = findViewById(R.id.lottienews);
        lottietodo = findViewById(R.id.lottietodo);
        lottienotes = findViewById(R.id.lottienotes);
        lottiequotes = findViewById(R.id.lottiequotes);

        // Play Lottie animations
        lottienews.loop(true);
        lottienews.playAnimation();
        lottietodo.loop(true);
        lottietodo.playAnimation();
        lottienotes.loop(true);
        lottienotes.playAnimation();
        lottiequotes.loop(true);
        lottiequotes.playAnimation();

        // Night mode preference
        sharedPreferences = getSharedPreferences("night", 0);
        boolean nightMode = sharedPreferences.getBoolean("night_mode", false);
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        // Retrieve nickname from SharedPreferences
        SharedPreferences retrieve = getSharedPreferences("login", MODE_PRIVATE);
        String user_nick_name = retrieve.getString("NAME", "");
        textView.setText("Hello " + user_nick_name + " 👋");

        // Set nickname in Navigation Drawer header
        View header = navigationView.getHeaderView(0);
        TextView nicknameText = header.findViewById(R.id.userName);
        nicknameText.setText(user_nick_name);

        // Button listeners
        btn1.setOnClickListener(view -> {
            Intent quotesIntent = new Intent(MainActivity.this, QuotesActivity.class);
            startActivity(quotesIntent);
        });

        btn2.setOnClickListener(view -> {
            Intent todoIntent = new Intent(MainActivity.this, TodoActivity.class);
            startActivity(todoIntent);
        });

        btn3.setOnClickListener(view -> {
            Intent notesIntent = new Intent(MainActivity.this, NotesActivity.class);
            startActivity(notesIntent);
        });

        btn4.setOnClickListener(view -> {
            Intent newsIntent = new Intent(MainActivity.this, EntrepreneurNewsActivity.class);
            startActivity(newsIntent);
        });

        // Open navigation drawer
        toolbar.setNavigationOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

        // Navigation item selection handler
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            item.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);

            if (id == R.id.home) {
                Toast.makeText(MainActivity.this, "You are already on the Home Page", Toast.LENGTH_SHORT).show();

            } else if (id == R.id.settings) {
                Intent intent1 = new Intent(getApplicationContext(), SettingsActivity.class);
                intent1.putExtra("Name", user_nick_name);
                startActivity(intent1);

            } else if (id == R.id.devGroup) {
                Intent intent4 = new Intent(getApplicationContext(), DevelopersActivity.class);
                startActivity(intent4);

            } else if (id == R.id.nav_about) {
                Intent intent2 = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(intent2);

            } else if (id == R.id.nav_privacy_policy) {
                Intent intent3 = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                startActivity(intent3);
            }

            return true;
        });
    }
}
