package com.example.orca.okhttp.listener;

/**
 * Created by orca on 2017/4/2.
 */

public interface DisposeDataListener {
    public void onSuccess(Object responseObj);
    public void onFailure(Object reasonObj);

}
