package com.example.app.javaiodemo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QueueDemo {

    private Queue<ActivityBean> activityQueue = new LinkedList<>();
    private ActivityBean currentBean;
    private static QueueDemo mInstance;

    private QueueDemo() {}

    public static QueueDemo getInstance() {
        if (mInstance == null) {
            synchronized (ReentranLockDemo.class) {
                if (mInstance == null) {
                    mInstance = new QueueDemo();
                }
            }
        }
        return mInstance;
    }

    public synchronized void offer(ActivityBean activityBean){
        if (activityBean!=null){
            activityQueue.offer(activityBean);
        }
    }

    public synchronized ActivityBean poll(){
        if (currentBean==null){
            currentBean = activityQueue.poll();
            //startactivity show  oncre onresume
        }
        return currentBean;
    }

    public synchronized void setNullCurrent(){
        currentBean = null;
    }
}
