package com.mike.coordinatorlayoutexample.collapsingtoolbarlayoutexample2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

public class HeadScaleBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private static String TAG = "HeadScaleBehavior";

    public HeadScaleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
        //Log.d(TAG, "" + dependency.getY());
        //set position
        int centerY = (int) ((dependency.getBottom() - dependency.getTop() + dependency.getY()) / 2);
        child.setY(centerY - child.getHeight() / 2);

        //set scale and alpha
        float ratio = (-dependency.getY()) / (((AppBarLayout) dependency).getTotalScrollRange());
        child.setScaleX(1.0f - ratio);
        child.setScaleY(1.0f - ratio);
        return true;
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {

        return dependency instanceof AppBarLayout;
    }
}
