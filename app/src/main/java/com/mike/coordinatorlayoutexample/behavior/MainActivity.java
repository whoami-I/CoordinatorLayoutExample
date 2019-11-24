package com.mike.coordinatorlayoutexample.behavior;


import android.view.MotionEvent;
import android.view.View;

import com.mike.coordinatorlayoutexample.R;
import com.mike.coordinatorlayoutexample.main.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.dependency)
    View dependency;


    @Override
    public int getContentViewId() {
        return R.layout.activity_behavior;
    }

    @Override
    public void afterSetContentView() {
        setTitle("Behavior");
        dependency.setOnTouchListener(new View.OnTouchListener() {

            int mOldX;
            int mOldY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                int currX = (int) event.getRawX();
                int currY = (int) event.getRawY();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mOldX = currX;
                        mOldY = currY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int offsetX = currX - mOldX;
                        int offsetY = currY - mOldY;
                        dependency.layout(
                                dependency.getLeft() + offsetX,
                                dependency.getTop() + offsetY,
                                dependency.getRight() + offsetX,
                                dependency.getBottom() + offsetY);
                        mOldX = currX;
                        mOldY = currY;
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:

                        break;
                }
                return true;
            }
        });
    }
}
