package com.example.orca.okhttp.listener;

/**
 * Created by orca on 2017/4/2.
 */

public class DisposeDataHandle {
    public DisposeDataListener mListener = null;
    public Class<?> mClass =null;
    public DisposeDataHandle(DisposeDataListener listener)
    {
        mListener=listener;
    }
    public DisposeDataHandle(DisposeDataListener listener,Class<?> clazz)
    {
        mListener=listener;
        mClass = clazz;
    }
}
