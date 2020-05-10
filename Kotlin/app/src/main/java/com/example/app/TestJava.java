package com.example.app;

public class TestJava {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100_0000; i++) {
                    if (isInterrupted()){
                        System.out.println("isInterrupted:"+isInterrupted());
                        return;
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        //收尾
                        //结束
                        System.out.println("sleep2:"+isInterrupted()); //这里返回 false
                        System.out.println("sleep:"+Thread.interrupted());//这里返回 false

                        //所以这里不结束的话，那么之后就没机会结束，后面的isInterrupted()都是返回false
                        return;
                    }
                    System.out.println("这是第"+i+"次数");
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
