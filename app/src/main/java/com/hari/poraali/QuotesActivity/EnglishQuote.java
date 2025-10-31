package com.hari.poraali.QuotesActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hari.poraali.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EnglishQuote extends Fragment {

    private static final String BASE_URL = "https://api.quotable.io/random?maxLength=120&tags=inspirational";

    private TextView quoteText;
    private TextView authorText;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_english_quote, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        quoteText = view.findViewById(R.id.jsontxtenglish);
        authorText = view.findViewById(R.id.authorname);
        progressBar = view.findViewById(R.id.progressBar);

        loadQuote();
    }

    private void loadQuote() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        quoteText.setText("");
        authorText.setText("");

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                BASE_URL,
                null,
                response -> {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    try {
                        String content = response.getString("content");
                        String author = response.getString("author");

                        quoteText.setText(content);
                        authorText.setText("- " + author);

                        Log.d("EnglishQuote", "Quote loaded: " + content);
                    } catch (JSONException e) {
                        Log.e("EnglishQuote", "JSON parsing error", e);
                        showError();
                    }
                },
                error -> {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    Log.e("EnglishQuote", "Network error", error);
                    showError();
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    private void showError() {
        quoteText.setText("Failed to load quote");
        authorText.setText("");
        if (getContext() != null) {
            Toast.makeText(getContext(), "Failed to load quote. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}