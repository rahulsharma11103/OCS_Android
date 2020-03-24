package com.example.snappingrecyclerviewexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context mContext = this;
        mRecyclerView = findViewById(R.id.recycler_view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int parentWidth = displayMetrics.widthPixels;
        int itemWidth = (int) getResources().getDimension(R.dimen.recycler_view_item_width);
        CustomLayoutManager customLayoutManager = new CustomLayoutManager(mContext, parentWidth, itemWidth);
        mRecyclerView.setLayoutManager(customLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(adapter);
        LinearSnapHelper snapHelper  = new LinearSnapHelper();
        addSnapChangeListener(snapHelper, customLayoutManager);
        snapHelper.attachToRecyclerView(mRecyclerView);
    }

    //Provide the snapping position
    //If it is not called that means the snapping position is 0 as the user haven't started the scrolling yet
    private void addSnapChangeListener(final SnapHelper snapHelper,
                                       final CustomLayoutManager customLayoutManager) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean mScrolled = false;
            int snapPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mScrolled) {
                    mScrolled = false;
                    final View currentView = snapHelper.findSnapView(customLayoutManager);
                    if (currentView == null) return;    // null when layout manager doesn't scroll
                    int pos = customLayoutManager.getPosition(currentView);
                    if (snapPosition != pos) {
                        snapPosition = pos;
                        Log.d(TAG, "Snap Pos: " + snapPosition);
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx != 0 || dy != 0) {
                    mScrolled = true;
                }
            }
        });
    }
}
