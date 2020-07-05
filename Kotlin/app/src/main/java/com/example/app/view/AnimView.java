package com.example.app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

public class AnimView extends View {
    private Bitmap bitmap;
    private List<Bitmap> bitmapList;
    private int index;
    public AnimView(Context context) {
        super(context);
    }

    public AnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmapList==null || bitmapList.size()==0) return;
        canvas.drawBitmap(bitmapList.get(index),0,0,null);
        index++;
        index = index%bitmapList.size();
    }

    public void setBitmapList(List<Bitmap> list){
        bitmapList = list;
    }
}
