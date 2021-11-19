package com.bawei.my_project_mvvm;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {




    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {

            View childAt = getChildAt(i);

            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
        }

//        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }


    int left = 0;
    int top = 0;
    int childSum = 0;


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        int childCount = getChildCount();


        for (int i = 0; i <childCount ; i++) {


            View childAt = getChildAt(i);
//            marginParams = (MarginLayoutParams) childAt.getLayoutParams();
//            //子元素所占宽度 = MarginLeft+ child.getMeasuredWidth+MarginRight  注意此时不能child.getWidth,因为界面没有绘制完成，此时wdith为0
//            int childWidth = marginParams.leftMargin + marginParams.rightMargin + childAt.getMeasuredWidth();
//            int childHeight = marginParams.topMargin + marginParams.bottomMargin + childAt.getMeasuredHeight();

//            if (i==0){
//
//                childAt.layout(left,top,childAt.getMeasuredWidth(),childAt.getMeasuredHeight());
//
//                left += childAt.getMeasuredWidth();
//                childSum += childAt.getMeasuredWidth();
//
//                continue;
//            }

            childSum += childAt.getMeasuredWidth();

            if (childSum < getMeasuredWidth()){ //所有子控件长度的和 小于 父容器的长度

                childAt.layout(left,top,childAt.getMeasuredWidth()+left,childAt.getMeasuredHeight()+top);

                left += childAt.getMeasuredWidth();

            }else {

                top += childAt.getMeasuredHeight();

                childSum = childAt.getMeasuredWidth();

                left = 0;

                childAt.layout(left,top,childAt.getMeasuredWidth()+left,childAt.getMeasuredHeight()+top);

                left += childAt.getMeasuredWidth();

            }

        }

    }
}
