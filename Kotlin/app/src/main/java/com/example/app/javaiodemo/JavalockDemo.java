package com.example.app.javaiodemo;

public class JavalockDemo {
    public static void main(String[] args) {
        thread1();
    }

    public static void thread1(){
        ReentranLockDemo.getInstance().startDemo();
    }
    public static void thread2(){
        ReentranLockDemo.getInstance().startDemo();
    }
}
