package com.example.app.lockdemo;

public class SingleInstance {
    private static volatile SingleInstance sInstance;

    public SingleInstance (){}

    public SingleInstance getInstance(){
        if (sInstance==null){
            synchronized (SingleInstance.class){
                if (sInstance==null){
                    sInstance = new SingleInstance();
                }
            }
        }
        return sInstance;
    }
}
