package com.example.app.fanxing;

import com.example.app.fanxing.bean.Food;

public interface Eater2<T extends Food> {
    void eat(T food);

    <R> void eat2(R item);
}
