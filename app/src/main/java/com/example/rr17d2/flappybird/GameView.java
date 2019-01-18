package com.example.rr17d2.flappybird;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.os.Handler;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GameView extends View {
    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLS = 30;
    Bitmap background;
    Display display;
    Point point;
    int dWidth, dHeight;
    Rect rect;

    Bird bird;
    PipeManager pipeManager;
    Context context;
    Score score;
    Paint scorePaint;

    public GameView(Context context) {
        super(context);
        this.context = context;
        handler = new Handler();
        //runnable = new Runnable() {
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0, 0, dWidth, dHeight);

        runnable = new Runnable() {

            @Override
            public void run() {
                invalidate();
            }
        };

        bird = new Bird(context, dWidth / 3, dHeight / 2, dHeight);
        pipeManager = new PipeManager(context, 0, 0, dWidth, dHeight);
        score = new Score(bird, pipeManager, dWidth / 2);

        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setStyle(Paint.Style.FILL);
        scorePaint.setTextSize(dHeight / 30);
        scorePaint.setTypeface(Typeface.SANS_SERIF);
        scorePaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!bird.alive && bird.pos.y > dHeight + 100) {
            //Context ctx = context.getApplicationContext();
            //Intent intent = new Intent(ctx, MainActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            //ctx.startActivity(intent);
            Activity act = (Activity) getContext();
            if(MainActivity.highScore < score.getScore()) {
                MainActivity.highScore = score.getScore();
                MainActivity.db.saveHighScore(score.getScore());

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference dbRef = db.getReference("Score");

                dbRef.child("Timmy").setValue(score.getScore());
            }
            act.setContentView(R.layout.activity_main);
            return;
        }

        canvas.drawBitmap(background, null, rect, null);
        pipeManager.drawIt(canvas);
        bird.drawIt(canvas);

        if(bird.alive) {
            pipeManager.update();
            checkCollisions();
            //score.update();
        }
        //score.drawIt(canvas);
        canvas.drawText(String.valueOf(score.getScore()), dWidth/2, dHeight - 300, scorePaint);
        handler.postDelayed(runnable, UPDATE_MILLS);
    }

    private void checkCollisions() {
        for(int i = 0; i<pipeManager.pipes.length; i++) {
            if(bird.collisionWithSolidObject(pipeManager.pipes[i])) {
                bird.alive = false;
                bird.setVelocity(Global.FLAP_POWER);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN && bird.alive) {
            bird.setVelocity(Global.FLAP_POWER);


        }
        return true;
    }

}
