package com.mike.coordinatorlayoutexample.appbarlayoutexample;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.mike.coordinatorlayoutexample.R;
import com.mike.coordinatorlayoutexample.base.BaseRecyAdapter;
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
    @Override
    public int getContentViewId() {
        return R.layout.activity_applayoutexample;
    }

    @Override
    public void afterSetContentView() {
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
                    mAppBarLayout.setExpanded(true,false);
                    return true;
                };
            }
        });


    }
}
