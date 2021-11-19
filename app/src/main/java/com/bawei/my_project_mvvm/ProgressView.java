package com.bawei.my_project_mvvm;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class ProgressView extends View {

    private Paint mPaint;
    private Paint txtPaint;


    //进度条颜色
    private int mColor = Color.GRAY;
    private int strokeWidth = 5;
    private int textColor =Color.RED;
    private int textSize = 20;

    private float swweepAngle =360;


    int defaultWidth = 100;
    int defaultHeight = 100;

    private int content = 5;

    int padding = 10;

    private int centerX;
    private int centerY;

    private ProgressVeiwListener listener;


    public void init(ProgressVeiwListener progressVeiwListener){
        this.listener = progressVeiwListener;
    }



    public ProgressView(Context context) {
        super(context);
        initPaint();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        doTypedArray(context,attrs);

        initPaint();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        txtPaint = new Paint();
        txtPaint.setColor(textColor);
        txtPaint.setTextSize(textSize);
        txtPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,defaultHeight);
        }else if (widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,heightSize);
        }else if (heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,defaultHeight);
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = getMeasuredWidth()/2;
        centerY = getMeasuredHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        TimeDown();

        //获取内间距
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        RectF rectF = new RectF(0 + padding, 0 + padding, getMeasuredWidth() - padding, getMeasuredHeight() - padding);
        canvas.drawArc(rectF,0,swweepAngle,false,mPaint);

        Rect bounds = new Rect();
        String text = String.valueOf(content)+"S";
        txtPaint.getTextBounds(text,0,text.length(),bounds);
        float offSet = (bounds.top + bounds.bottom)/2;

        canvas.drawText(text,centerX,centerY-offSet,txtPaint);

    }


    private void doTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        mColor=typedArray.getColor(R.styleable.ProgressView_strokeC,mColor);
        strokeWidth=typedArray.getInteger(R.styleable.ProgressView_strokeW,strokeWidth);
        textColor=typedArray.getColor(R.styleable.ProgressView_textColor,textColor);
        textSize=typedArray.getInteger(R.styleable.ProgressView_textSize,textSize);
        typedArray.recycle();
    }

    private void  TimeDown(){

        if (swweepAngle /72 <=1){
            content =1;
        }

        if (swweepAngle /72 >1 && swweepAngle /72 <=2){
            content =2;
        }

        if (swweepAngle /72 >2 && swweepAngle /72 <=3){
            content =3;
        }

        if (swweepAngle /72 >3 && swweepAngle /72 <=4){
            content =4;
        }

        if (swweepAngle /72 >4){
            content =5;
        }
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startDoAnimator();
    }

    private void startDoAnimator() {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);

        valueAnimator.setDuration(5000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {


                float animatedValue = (float) animation.getAnimatedValue();



                swweepAngle = 360 - animatedValue*360;


                Log.i("1213", "onAnimationUpdate: "+swweepAngle);


                if (Looper.getMainLooper().getThread() == Thread.currentThread()){
                        invalidate();
                }else {
                        postInvalidate();
                }
            }
        });
        valueAnimator.start();

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                listener.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    public interface ProgressVeiwListener{
        void finish();
    }
}
