package com.example.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.example.app.view.UIUtil;


/**
 * Create by Don on 2020/7/4
 * DESC:城市雷达图——进度条
 */
public class ProgressView extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private int INTERVAL_PADDING = UIUtil.dp2px(20) + UIUtil.dp2px(30);
    private int Y_INTERVAL_PADDING = UIUtil.dp2px(5);
    private int ITEM_INTERVAL = UIUtil.dp2px(65);
    private int LINE_LENGTH = UIUtil.dp2px(13);
    private float offsetX = 0f;
    private float minvalue = 0f;
    private float maxValue = 0f;
    private ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "offsetX", minvalue, maxValue);
    private Bitmap playButton;
    private Bitmap stopButton;
    private OnclickListener listener;
    private boolean isPlay = true;

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
        invalidate();
    }

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Resources res = getResources();
        paint.setColor(Color.parseColor("#E6E6E6"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        textPaint.setColor(Color.parseColor("#333333"));
        textPaint.setTextSize(UIUtil.dp2px(14));


        Drawable stopDrawable = ContextCompat.getDrawable(getContext(), R.drawable.icon_stop);
        stopButton = ((BitmapDrawable) stopDrawable).getBitmap();

        Drawable playdrawable = ContextCompat.getDrawable(getContext(), R.drawable.icon_location);
        playButton = ((BitmapDrawable) playdrawable).getBitmap();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        maxValue = getMeasuredWidth() - 2 * INTERVAL_PADDING + UIUtil.dp2px(20);
        objectAnimator.setFloatValues(minvalue, maxValue);
        objectAnimator.setDuration(20000);
        startObjectAnim();
    }

    private void startObjectAnim() {
        objectAnimator.start();
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startObjectAnim();
                invalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int minWidth = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int width = Math.max(minWidth, MeasureSpec.getSize(widthMeasureSpec));

        int minHeight = getPaddingBottom() + getPaddingTop() + getSuggestedMinimumHeight();
        int height = Math.max(minHeight, MeasureSpec.getSize(heightMeasureSpec));

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制背景
        drawBackground();
        //绘制线
        drawLine(canvas);
        //绘制底部文字
        drawText(canvas);
        //绘制指示器
        drawIndicator(canvas);
        //绘制开始按钮
        drawButton(canvas);

    }

    private void drawButton(Canvas canvas) {
        if (isPlay) {
            canvas.drawBitmap(stopButton, UIUtil.dp2px(10), getMeasuredHeight() / 2 - UIUtil.dp2px(5), paint);
        } else {
            canvas.drawBitmap(playButton, UIUtil.dp2px(10), getMeasuredHeight() / 2 - UIUtil.dp2px(5), paint);
        }
    }

    private void drawBackground() {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.corner_8_solid_ffd03f3f);
        setBackground(drawable);
    }

    private void drawLine(Canvas canvas) {
        for (int i = 0; i < 6; i++) {
            canvas.drawLine(INTERVAL_PADDING + i * ITEM_INTERVAL, 0, INTERVAL_PADDING + i * ITEM_INTERVAL, LINE_LENGTH, paint);
        }
    }

    private void drawText(Canvas canvas) {
        canvas.drawText("现在", INTERVAL_PADDING - UIUtil.dp2px(10), getMeasuredHeight() - Y_INTERVAL_PADDING, textPaint);
        textPaint.setColor(Color.parseColor("#666666"));
        canvas.drawText("1小时", getMeasuredWidth() / 2, getMeasuredHeight() - Y_INTERVAL_PADDING, textPaint);
        canvas.drawText("2小时", getMeasuredWidth() - INTERVAL_PADDING, getMeasuredHeight() - Y_INTERVAL_PADDING, textPaint);
    }

    private void drawIndicator(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.icon_indicator);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        canvas.drawBitmap(bitmap, INTERVAL_PADDING + offsetX, 0, paint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        objectAnimator.start();
    }

    public void setButtonOnclick(OnclickListener buttonOnclick) {
        listener = buttonOnclick;
    }

    public interface OnclickListener {
        void onClick();
    }


    public void playOrStop() {
        if (objectAnimator == null) return;
        isPlay = !isPlay;
        if (isPlay) {
            objectAnimator.resume();
        } else {
            objectAnimator.pause();
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float downX = event.getX();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:
                if (downX > 0 && downX < UIUtil.dp2px(30)) {
                    if (listener != null) {
                        listener.onClick();
                    }
                }
                break;
        }
        return true;
    }
}
