package com.example.rr17d2.flappybird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static long highScore = 0;
    static Database db;
    static TextView highScoreTextView;
    static ListView scoreboardListView;
    static ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        highScoreTextView = findViewById(R.id.highScoreTextView);
        highScoreTextView.setText(String.valueOf(highScore));
        String name = getIntent().getStringExtra("name");
        db = new Database(name);
        scoreboardListView = findViewById(R.id.scoreboardListView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, db.scores);
        scoreboardListView.setAdapter(adapter);
        scoreboardListView.setSelection(adapter.getCount() - 1);

        adapter.notifyDataSetChanged();

    }
    public void startGame(View view) {
        Log.d("click", "start game");
        GameView gameView = new GameView(this);
        setContentView(gameView);
        //Intent intent = new Intent(this, StartGame.class);
        //startActivity(intent);
        //finish();
    }
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        TextView highScoreTextView = findViewById(R.id.highScoreTextView);

        if(highScoreTextView !=null) {
            highScoreTextView.setText(String.valueOf(highScore));
        }
        scoreboardListView = (ListView)findViewById(R.id.scoreboardListView);
        if(scoreboardListView != null & adapter != null) {
            scoreboardListView.setAdapter(adapter);
            scoreboardListView.setSelection(adapter.getCount() - 1);
            adapter.notifyDataSetChanged();
        }
    }
}
