package com.mike.coordinatorlayoutexample.collapsingtoolbarlayoutexample1;


import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mike.coordinatorlayoutexample.R;
import com.mike.coordinatorlayoutexample.base.BaseRecyAdapter;
import com.mike.coordinatorlayoutexample.base.DisplayUtils;
import com.mike.coordinatorlayoutexample.main.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.coordinator)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    public int getContentViewId() {
        return R.layout.activity_collapsingtoolbarlayoutexample1;
    }

    @Override
    public void afterSetContentView() {
        // init padding
        FrameLayout.LayoutParams lps = (FrameLayout.LayoutParams) mToolBar.getLayoutParams();
        lps.topMargin = DisplayUtils.getStatusBarHeight(this);
        AppBarLayout.OnOffsetChangedListener listener = new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("haha", "" + verticalOffset);
                // get total height and calc ratio
                int totalHeight = mCollapsingToolbarLayout.getHeight() - mToolBar.getHeight() - ((FrameLayout.LayoutParams) mToolBar.getLayoutParams()).topMargin;
                float ratio = getRatio(-verticalOffset, totalHeight);
                changeToolBar(ratio);
                changeStatusBar(ratio);
            }
        };
        changeToolBar(0);


        ViewTreeObserver vto = mAppBarLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mAppBarLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // 防止多次add
                mAppBarLayout.removeOnOffsetChangedListener(listener);
                mAppBarLayout.addOnOffsetChangedListener(listener);
            }
        });

        // init recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("this is " + i + " item");
        }
        recyclerView.setAdapter(new BaseRecyAdapter(recyclerView, datas) {
            @Override
            public View.OnClickListener setItemClickListener(ViewHolder holder, int position) {
                return null;
            }

            @Override
            public View.OnLongClickListener setItemLongClickListener(ViewHolder holder, int position) {
                return v -> {
                    mAppBarLayout.setExpanded(true, false);
                    return true;
                };
            }
        });
    }

    private void changeStatusBar(float ratio) {
        int transparent = (int) (255 * ratio);
        int color = 0x008577 | (transparent << 24);
        getWindow().setStatusBarColor(color);
    }

    private void changeToolBar(float ratio) {
//        Drawable drawable = mToolBar.getBackground();
//        drawable = drawable.mutate();
//        int alpha= (int) (ratio*255);
//        drawable.setAlpha(alpha);
        mToolBar.setAlpha(ratio);
    }

    @Override
    public void beforeSetContentView() {
        super.beforeSetContentView();
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    private float getRatio(int cur, int total) {
        float ratio = 0.0f;
        int offset = 100;
        if (cur >= total - offset) {
            cur = cur - (total - offset);
            total = offset;
            ratio = cur * 1.0f / total;
            ratio = ratio > 1.0f ? 1.0f : ratio;
        }
        return ratio;
    }
}
