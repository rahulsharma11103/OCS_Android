package com.example.rxexample;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    @NonNull
    List<Integer> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listData = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            listData.add(i);
        }
        //Obeserable for  create
        // observableUsingCreate.subscribe(observer);

        //Obeserable for from
        //  observableUsingFrom.subscribe(observerForInterger);


        Observable.interval(5, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.d("Time value:", aLong.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    Observable observableUsingCreate = Observable.create(new ObservableOnSubscribe() {
        @Override
        public void subscribe(ObservableEmitter e) throws Exception {

            for (Integer value : listData) {
                e.onNext(value);
            }

            e.onComplete();
        }
    });


    Observable observableUsingFrom = Observable.fromArray(new Integer[]{1, 2, 3, 4, 5});


    Observer observerForInterger = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d("onSubscribe", "subscribed to observer");

        }

        @Override
        public void onNext(Integer integer) {
            Log.d("onNext", integer.toString());
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
            Log.d("onComplete", "completed");

        }
    };


    Observer observer = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d("onSubscribe", "subscribed to observer");
        }

        @Override
        public void onNext(Object o) {
            Log.d("onNext", o.toString());
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
            Log.d("onComplete", "completed");
        }
    };

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
