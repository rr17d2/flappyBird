package com.example.rr17d2.flappybird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Intent intent;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intent = new Intent(this, MainActivity.class);
        nameEditText = (EditText) findViewById(R.id.nameEditText);

        nameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == event.KEYCODE_ENTER) {
                    intent.putExtra("name", nameEditText.getText().toString());
                    startActivity(intent);
                }
                return true;
            }

        });
    }
}
