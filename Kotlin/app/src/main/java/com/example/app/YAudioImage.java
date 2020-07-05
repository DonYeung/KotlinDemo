package com.example.app;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.app.view.UIUtil;

import java.util.List;


public class YAudioImage extends AppCompatImageView {

    private List<Bitmap> bitmapList;
    private Paint textPaint;
    private String time;
    private boolean isPlay;
    private int index;

    public YAudioImage(Context context) {
        this(context, null, 0);
    }

    public YAudioImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YAudioImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
       /* srcBitmap = new Bitmap[]{BitmapFactory.decodeResource(getResources(), R.drawable.gps_point),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_avatar),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_avatar2)};*/
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(UIUtil.dp2px(18) );
        startAudio(true);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (bitmapList==null || bitmapList.size()==0){
            return;
        }
        /*if (!TextUtils.isEmpty(time)) {
            // 计算一下time的宽度
            float textWidth = textPaint.measureText(time);
            float mWidth = textPaint.measureText(String.valueOf(time.charAt(time.length() - 1)));
            canvas.drawText(time, (canvas.getWidth() - textWidth + mWidth) / 2, UIUtil.dp2px(20) , textPaint);
        }*/
       /* canvas.drawBitmap(srcBitmap[index], (canvas.getWidth() - srcBitmap[index].getWidth()) / 2, (canvas.getHeight() +
                UIUtil.dp2px(20) - srcBitmap[index].getHeight()) / 2, null);*/
        canvas.drawBitmap(bitmapList.get(index),(canvas.getWidth() - bitmapList.get(index).getWidth()) / 2, (canvas.getHeight() +
                UIUtil.dp2px(20) - bitmapList.get(index).getHeight()) / 2, null);
        if (isPlay) {
            index++;
            index = index % bitmapList.size();
            postInvalidateDelayed(250);
        }
    }

    public void setTime(String time) {
        this.time = time;
        invalidate();
    }

    public void startAudio(boolean isPlay) {
        this.isPlay = isPlay;
        this.index = 0;
        invalidate();
    }


    public void setBitmapList(List<Bitmap> list){
        bitmapList = list;
        postInvalidate();
    }


}
