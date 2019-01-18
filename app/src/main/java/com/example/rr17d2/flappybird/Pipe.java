package com.example.rr17d2.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Pipe extends SolidObject {
    Bitmap img;
    int imgHeight, imgWidth;

    public Pipe(Context context, int x, int y, int type) {
        super(context, x, y, "Pipe");
        int imgId = Global.BOTTOM_TUBE == type ? R.drawable.bottomtube : R.drawable.toptube;
        img = BitmapFactory.decodeResource(getResources(), imgId);
        imgHeight = img.getHeight();
        imgWidth = img.getWidth();

        }

        protected void update() {
        pos.x -= Global.PIPE_SPEED;
        rect.top = pos.y;
        rect.bottom = pos.y + imgHeight;
        rect.left = pos.x;
        rect.right = pos.x + imgWidth;

        }
        public void  drawIt(Canvas canvas) {
        canvas.drawBitmap(img, pos.x, pos.y, null);

        }
}
