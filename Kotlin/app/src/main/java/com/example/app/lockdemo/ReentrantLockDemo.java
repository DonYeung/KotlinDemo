package com.example.app.lockdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 可重入锁--对读和写分别锁，操作性更灵活
 */
public class ReentrantLockDemo {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();
     private int name;
     private int age;
     //写操作
    private void add(){
        writeLock.lock();
        try {
            name++;
            age++;
        }finally {
            writeLock.unlock();
        }
    }
    //读操作
    private void get(){
        readLock.lock();
        try{
            System.out.println("name"+name);
            System.out.println("age"+age);
        }finally {
            readLock.unlock();
        }
    }


    public static void main(String[] args) {

    }
}
