package com.example.liqingju.meituancontext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by liqingju on 15/12/30.
 */
public class CrilyView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Context mContext;
    private int mColor;

    public CrilyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDeft(attrs,context);
    }


    public CrilyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDeft(attrs,context);
    }


    public CrilyView(Context context) {
        super(context);
    }

    private void initDeft(AttributeSet attrs, Context context) {
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CrilyView);
        mColor = typedArray.getColor(R.styleable.CrilyView_color,Color.RED);
        typedArray.recycle();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int sizeSpec = MeasureSpec.getMode(widthMeasureSpec);

        int hight = MeasureSpec.getSize(heightMeasureSpec);
        int sizeHight = MeasureSpec.getMode(heightMeasureSpec);
        if (sizeSpec == MeasureSpec.AT_MOST && sizeHight == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200);
        } else if (sizeSpec == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, hight);
        } else if (sizeHight == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, 200);
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        int wight = getWidth() - paddingLeft - paddingRight;
        int hight = getHeight() - paddingBottom - paddingTop;
        int radius = Math.min(wight, hight) / 2;
        mPaint.setColor(mColor);
        canvas.drawCircle(paddingLeft + wight / 2, paddingTop + hight / 2, radius, mPaint);


    }
}
