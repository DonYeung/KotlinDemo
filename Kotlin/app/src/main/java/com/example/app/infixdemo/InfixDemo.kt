package com.example.app.infixdemo

infix fun<A,B> A.with(that:B):Pair<A,B> = Pair(this,that)

fun main(){
    val map = mapOf("apple" with 1,"banana" with 2)
}