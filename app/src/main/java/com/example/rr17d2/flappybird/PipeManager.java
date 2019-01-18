package com.example.rr17d2.flappybird;

import android.content.Context;
import android.graphics.Canvas;

import java.util.Random;

public class PipeManager extends GameObject {
    Pipe[] pipes;
    int dWidth, dHeight;
    Context context;
    Random randy;

    public PipeManager(Context context, int x, int y, int dWidth, int dHeight) {
        super(context, x, y);
        this.dWidth = dWidth;
        this.dHeight = dHeight;
        this.context = context;
        randy = new Random();
        pipes = new Pipe[2];
        newPipeGate();
    }
    public void newPipeGate() {
        int gateGap = dHeight / 4;
        int gatePos = randy.nextInt((gateGap * 3 - gateGap)) + gateGap;
        pipes[0] = new Pipe(context, dWidth + 400, gatePos - gateGap / 2, R.drawable.toptube);
        pipes[1] = new Pipe(context, dWidth + 400, gatePos + gateGap / 2, R.drawable.bottomtube);
        pipes[0].pos.y -= pipes[0].img.getHeight();
    }
    protected void update() {
        for(int i=0; i<pipes.length; i++) {
            pipes[i].update();
        }
        if(pipes[0].pos.x < -400) {
            newPipeGate();
        }
        //pipes[0].update();
        //pipes[1].update();

    }
    public void drawIt(Canvas canvas) {
        for(int i = 0; i<pipes.length; i++) {
            pipes[i].drawIt(canvas);
        }
    }
}
