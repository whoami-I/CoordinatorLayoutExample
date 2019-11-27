package com.mike.coordinatorlayoutexample.collapsingtoolbarlayoutexample2;


import android.graphics.Color;
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

    int mHeight;
    @Override
    public int getContentViewId() {
        return R.layout.activity_collapsingtoolbarlayoutexample2;
    }

    @Override
    public void afterSetContentView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        FrameLayout.LayoutParams lps = (FrameLayout.LayoutParams) mToolBar.getLayoutParams();
        lps.topMargin = DisplayUtils.getStatusBarHeight(this);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("haha",""+verticalOffset);
            }
        });

        ViewTreeObserver vto = mAppBarLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mAppBarLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mCollapsingToolbarLayout.setScrimVisibleHeightTrigger(mToolBar.getHeight() + 1 + DisplayUtils.getStatusBarHeight(MainActivity.this));
                mHeight = 0;
            }
        });

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

    @Override
    public void beforeSetContentView() {
        super.beforeSetContentView();
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }
}
