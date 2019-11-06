package com.mike.coordinatorlayoutexample.Behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewParent;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.mike.coordinatorlayoutexample.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyBehavior extends CoordinatorLayout.Behavior<CircleImageView> {

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        if (child.getId() == R.id.child) {
            return dependency.getId() == R.id.dependency;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {

        //根据dependency的位置，btn
        int totalWidth = ((View) dependency.getParent()).getWidth();
        int totalHeight = ((View) dependency.getParent()).getHeight();

        int top = dependency.getTop();
        int left = dependency.getLeft();

        int x = totalWidth - left - child.getWidth();
        int y = totalHeight - top - child.getHeight();

        setPosition(child, x, y);
        return true;
    }

    private void setPosition(View v, int x, int y) {
        v.layout(x, y, x + v.getWidth(), y + v.getHeight());
    }


}
