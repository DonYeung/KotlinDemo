package com.example.app.fanxing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReversableArrayList<T> extends ArrayList<T> {
    public void reverse(){
        Collections.reverse(this);
    }
}
