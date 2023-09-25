package com.mathinfo.androidtpnote;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import com.mathinfo.androidtpnote.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mathinfo.androidtpnote.databinding.ContentMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ContentMainBinding contentMainBinding;

    private int answer = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        contentMainBinding = binding.main;

        setSupportActionBar(binding.toolbar);



        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        Button generateButton = findViewById(R.id.startButton);
        generateButton.setOnClickListener(v -> start());

        Button answerButton = findViewById(R.id.answerButton);
        answerButton.setOnClickListener(v -> answer());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void start() {
        EditText editTextValue = findViewById(R.id.editTextValue);
        int value = Integer.parseInt(editTextValue.getText().toString());
        answer  = new Random().nextInt(value + 1);
        Log.d("Answer", "Generated random number: " + answer);
        hideStartForm();
        showGameForm();
    }

    private void answer() {
        EditText editTextGuess = findViewById(R.id.editTextGuess);
        int value = Integer.parseInt(editTextGuess.getText().toString());
        Log.d("Input", "Input number: " + value);

        TextView resultTextView = findViewById(R.id.resultTextView);
        ImageView imageView = findViewById(R.id.imageView);

        if (value == answer) {
            Log.d("Resultat", "Victoire");
            hideGameForm();
            showStartForm();
            imageView.setVisibility(View.VISIBLE);
        } else if (value < answer) {
            resultTextView.setText(getString(R.string.more));
            imageView.setVisibility(View.GONE);
            Log.d("Resultat", "Plus");
        } else {
            resultTextView.setText(getString(R.string.less));
            imageView.setVisibility(View.GONE);
            Log.d("Resultat", "Moins");
        }
    }

    // utiliser deux layouts differents a la place

    private void hideStartForm() {
        TextView textViewValue = findViewById(R.id.textViewValue);
        EditText editTextValue = findViewById(R.id.editTextValue);
        Button startButton = findViewById(R.id.startButton);
        ImageView imageView = findViewById(R.id.imageView);

        textViewValue.setVisibility(View.GONE);
        editTextValue.setVisibility(View.GONE);
        startButton.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
    }

    private void showStartForm() {
        TextView textViewValue = findViewById(R.id.textViewValue);
        EditText editTextValue = findViewById(R.id.editTextValue);
        Button startButton = findViewById(R.id.startButton);

        textViewValue.setVisibility(View.VISIBLE);
        editTextValue.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.VISIBLE);
        editTextValue.setText("");
    }

    private void hideGameForm() {
        TextView textViewGuess = findViewById(R.id.textViewGuess);
        EditText editTextGuess = findViewById(R.id.editTextGuess);
        Button answerButton = findViewById(R.id.answerButton);
        TextView resultTextView = findViewById(R.id.resultTextView);

        textViewGuess.setVisibility(View.GONE);
        editTextGuess.setVisibility(View.GONE);
        answerButton.setVisibility(View.GONE);
        resultTextView.setVisibility(View.GONE);
    }

    private void showGameForm() {
        TextView textViewGuess = findViewById(R.id.textViewGuess);
        EditText editTextGuess = findViewById(R.id.editTextGuess);
        Button answerButton = findViewById(R.id.answerButton);
        TextView resultTextView = findViewById(R.id.resultTextView);

        textViewGuess.setVisibility(View.VISIBLE);
        editTextGuess.setVisibility(View.VISIBLE);
        answerButton.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        editTextGuess.setText("");
    }

}