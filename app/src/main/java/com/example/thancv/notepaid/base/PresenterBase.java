package com.example.thancv.notepaid.base;

/**
 * Created by ThanCV on 8/22/2017.
 */

public interface PresenterBase<V> {
    void onCreate(V v);

    void onStart();

    void onResume();

    void onStop();

    void onDestroy();
}
