package com.example.rr17d2.flappybird;

import android.content.Context;
import android.graphics.Rect;


public class SolidObject extends GameObject {
    String tag;
    Rect rect;

    public SolidObject(Context context, int x, int y, String tag) {
        super(context, x, y);
        this.tag = tag;
        rect = new Rect();
    }


    public String getTag() {return this.tag; }

    public void setRect(Rect r) {this.rect = r; }

    public void setRect(int top, int bottom, int left, int right ){
        this.rect = new Rect(left, top, right, bottom);
    }

    public boolean collisionWithSolidObject(SolidObject obj) {
        return !(rect.right < obj.rect.left || obj.rect.right < rect.left ||
                rect.bottom < obj.rect.top || obj.rect.bottom < rect.top);

    }
}
