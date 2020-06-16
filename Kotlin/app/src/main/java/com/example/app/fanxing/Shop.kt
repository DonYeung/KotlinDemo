package com.example.app.fanxing


class AppShop<T> {
    fun buy(): T {
        return null as T
    }
    fun refund(item :T){

    }
}

private interface Shop<T> {
    fun buy(): T
    fun refund(item: T)
}

