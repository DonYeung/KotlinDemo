package com.example.app.fanxing;

interface Shop2<T> {
    public T bug();
    public void refun(T item);
    <E> E tranin(E item);
}
