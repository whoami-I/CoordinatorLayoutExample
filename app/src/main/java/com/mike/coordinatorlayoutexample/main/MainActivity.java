package com.mike.coordinatorlayoutexample.main;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mike.coordinatorlayoutexample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    List<ItemDataBean> mDataList = new ArrayList<>();

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void afterSetContentView() {
        initDataList();
        LinearLayoutManager llm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        HomeListAdapter homeListAdapter = new HomeListAdapter(this, mDataList);
        recyclerView.setAdapter(homeListAdapter);
    }

    private void initDataList() {
        mDataList.add(new ItemDataBean("Behavior",
                com.mike.coordinatorlayoutexample.behavior.MainActivity.class, ColorGenerator.getInstance().getColor()));
        mDataList.add(new ItemDataBean("AppBarLayoutExample",
                com.mike.coordinatorlayoutexample.appbarlayoutexample.MainActivity.class, ColorGenerator.getInstance().getColor()));
        mDataList.add(new ItemDataBean("CollapsingToolBarLayoutExample",
                com.mike.coordinatorlayoutexample.collapsingtoolbarlayoutexample.MainActivity.class, ColorGenerator.getInstance().getColor()));
        mDataList.add(new ItemDataBean("CollapsingToolBarLayoutZoom",
                com.mike.coordinatorlayoutexample.collapsingtoolbarlayoutzoom.MainActivity.class, ColorGenerator.getInstance().getColor()));
        mDataList.add(new ItemDataBean("CollapsingToolBarLayoutExample1",
                com.mike.coordinatorlayoutexample.collapsingtoolbarlayoutexample1.MainActivity.class, ColorGenerator.getInstance().getColor()));
        mDataList.add(new ItemDataBean("CollapsingToolBarLayoutExample2",
                com.mike.coordinatorlayoutexample.collapsingtoolbarlayoutexample2.MainActivity.class, ColorGenerator.getInstance().getColor()));
    }
}
