package com.mike.coordinatorlayoutexample.main;

import android.graphics.Color;

import java.util.Random;

public class ColorGenerator {
    private ColorGenerator() {
    }

    private static ColorGenerator mInstance;
    private static Random mRandom;

    public static ColorGenerator getInstance() {
        if (mInstance == null) {
            synchronized (ColorGenerator.class) {
                if (mInstance == null) {
                    mInstance = new ColorGenerator();
                }
            }
        }
        return mInstance;
    }

    public int getColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);
    }
}
