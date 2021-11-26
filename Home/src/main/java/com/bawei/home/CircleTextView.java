package com.bawei.home;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class CircleTextView extends androidx.appcompat.widget.AppCompatTextView {

    private Paint mPaint;
    private int  TextC = Color.BLUE;
    private RectF rectF;

    public CircleTextView( Context context) {
        super(context);
        init();
    }

    public CircleTextView( Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView);
        TextC = typedArray.getColor(R.styleable.CircleTextView_TextC,TextC);
        init();
    }

    public CircleTextView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(TextC);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF = new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(rectF,0,360,false,mPaint);
        super.onDraw(canvas);


    }
}
