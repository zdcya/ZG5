package com.bawei.resource.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    private Paint mPaint;
   private RectF rectF;
    public MyTextView(@NonNull  Context context) {
        super(context);
        initPaint();
    }

    public MyTextView(@NonNull  Context context, @Nullable  AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyTextView(@NonNull  Context context, @Nullable  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }
   public void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectF = new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(rectF,30.0f,30.0f,mPaint);
    }

}
