package com.example.rr17d2.flappybird;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.provider.Settings;
import android.util.Log;

public class Bird extends SolidObject {
    Bitmap[] imgs;
    int velocity = 0, imgFrame = 0;
    Matrix matrix;
    int imgCenterX, imgCenterY;
    int imgWidth, imgHeight;
    Paint p = new Paint();

    int dHeight;
    boolean alive = true;

    public Bird (Context context, int x, int y, int dHeight) {
        super(context, x, y, "Bird");
        this.dHeight = dHeight;

        imgs = new Bitmap[2];
        imgs[0]= BitmapFactory.decodeResource(getResources(), R.drawable.bird1);
        imgs[1]= BitmapFactory.decodeResource(getResources(), R.drawable.bird2);
        matrix = new Matrix();
        imgWidth = imgs[0].getWidth();
        imgHeight = imgs[0].getHeight();
        imgCenterX = imgWidth / 2;
        imgCenterY = imgHeight / 2;
        pos.x -= imgCenterX;




        p.setColor(Color.MAGENTA);
        p.setAlpha(80);

    }

    private void update() {
        int deg = 0;

        // Handle death from top of screen and bottom of screen
        if(alive && (pos.y < 0 - imgHeight || pos.y > dHeight)) {
            alive = false;
            velocity = Global.FLAP_POWER;
            }

            // Death Anim
        if(!alive) {
            deg = 180;
        }
        // Alive
        else {
            deg = velocity > 0 ? 15: -15;
            imgFrame = imgFrame > 0 ? 0 : 1;
        }
        velocity += Global.GRAVITY;
        pos.y += velocity;
       // deg = velocity < 0 ? 15 : -15;

        rect.top = pos.y;
        rect.left = pos.x;
        rect.bottom = pos.y + imgHeight;
        rect.right = pos.x + imgWidth;

        matrix.setRotate(deg, imgCenterX, imgCenterY);
        matrix.postTranslate(pos.x, pos.y);
        //imgFrame = imgFrame > 0 ? 0 : 1;
    }

    public void drawIt(Canvas canvas) {
        this.update();
        canvas.drawBitmap(imgs[imgFrame], matrix, null);

        if(Global.DEBUG) {
            canvas.drawRect(rect, p);
        }
        //Log.d("Pos Y", (String.valueOf(pos.y));
    }

    public void setVelocity(int vel) {
        this.velocity = vel;
    }
}
