package com.example.rr17d2.flappybird;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

public class Score {
    int score;
    Bird bird;
    PipeManager pipeManager;
    boolean scoreOnPipeSet = false;
    int midScreen;
    Paint paint;

    public Score(Bird b, PipeManager p, int midScreen ) {
        this.bird = b;
        this.pipeManager = p;
        score = 0;
        this.midScreen = midScreen;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(midScreen / 4);
        paint.setTypeface(Typeface.SANS_SERIF);
        paint.setTextAlign(Paint.Align.CENTER);
    }
    private void addToScore() {
        this.score++;
    }
    public int getScore() { return this.score; }
    public void update() {

        if(!scoreOnPipeSet && bird.rect.left > pipeManager.pipes[0].rect.right) {
            scoreOnPipeSet = true;
            addToScore();
            //Log.d("score", String.valueOf(this.score));
        }
        if (scoreOnPipeSet && bird.rect.right < pipeManager.pipes[0].rect.left) {
            scoreOnPipeSet = false;
        }
    }
    public void drawIt(Canvas canvas) {
        canvas.drawText(String.valueOf(this.score), midScreen, 200, paint );
    }


}
