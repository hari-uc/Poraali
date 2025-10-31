package com.hari.poraali.QuotesActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hari.poraali.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EnglishQuote extends Fragment {

    private static final String BASE_URL = "https://api.realinspire.live/v1/quotes/random";

    private TextView quoteText;
    private TextView authorText;
    private ProgressBar progressBar;
    private ImageView refreshButton;

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
        refreshButton = view.findViewById(R.id.refreshButton);

        loadQuote();

        // Refresh button click
        if (refreshButton != null) {
            refreshButton.setOnClickListener(v -> loadQuote());
        }
    }

    private void loadQuote() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (refreshButton != null) {
            refreshButton.setEnabled(false);
            refreshButton.setAlpha(0.5f);
        }
        quoteText.setText("");
        authorText.setText("");

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                BASE_URL,
                null,
                response -> {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    if (refreshButton != null) {
                        refreshButton.setEnabled(true);
                        refreshButton.setAlpha(1.0f);
                    }
                    try {
                        if (response.length() > 0) {
                            JSONObject quoteObject = response.getJSONObject(0);
                            String content = quoteObject.getString("content");
                            String author = quoteObject.getString("author");

                            quoteText.setText("\"" + content + "\"");
                            authorText.setText("— " + author);

                            Log.d("EnglishQuote", "Quote loaded: " + content);
                        }
                    } catch (JSONException e) {
                        Log.e("EnglishQuote", "JSON parsing error", e);
                        showError();
                    }
                },
                error -> {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    if (refreshButton != null) {
                        refreshButton.setEnabled(true);
                        refreshButton.setAlpha(1.0f);
                    }
                    Log.e("EnglishQuote", "Network error", error);
                    showError();
                }
        );

        requestQueue.add(jsonArrayRequest);
    }

    private void showError() {
        quoteText.setText("Unable to load quote at the moment");
        authorText.setText("");
        if (getContext() != null) {
            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
    }
}