package com.example.app.javaiodemo;

import android.util.Log;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranLockDemo {
    private static ReentranLockDemo mInstance;
    private Lock lock;
    public Condition condition ;

    private ReentranLockDemo() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public static ReentranLockDemo getInstance() {
        if (mInstance == null) {
            synchronized (ReentranLockDemo.class) {
                if (mInstance == null) {
                    mInstance = new ReentranLockDemo();
                }
            }
        }
        return mInstance;
    }

    public void setLock() { lock.lock();}

    public void setUnLock() { lock.unlock();}

    public void startDemo() {
        setLock();
        Log.i("Don", "startDemo: ");
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setUnLock();
    }


}
