package com.example.liqingju.meituancontext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.text.AttributedCharacterIterator;

/**
 * Created by liqingju on 15/12/31.
 */
public class MyClicleView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor = Color.RED;
    private Context mContext;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public MyClicleView(Context context) {
        super(context);
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p/>
     * <p/>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #View(Context, AttributeSet, int)
     */
    public MyClicleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    /**
     * Perform inflation from XML and apply a class-specific base style. This
     * constructor of View allows subclasses to use their own base style when
     * they are inflating. For example, a Button class's constructor would call
     * this version of the super class constructor and supply
     * <code>R.attr.buttonStyle</code> for <var>defStyle</var>; this allows
     * the theme's button style to modify all of the base view attributes (in
     * particular its background) as well as the Button class's attributes.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource to apply to this view. If 0, no
     *                     default style will be applied.
     * @see #View(Context, AttributeSet)
     */
    public MyClicleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CrilyView);
//        mColor = typedArray.getColor(R.styleable.CrilyView_color,Color.RED);
    }

    private void initView(Context context, AttributeSet atts) {

        this.mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(atts, R.styleable.CrilyView);
        mColor = typedArray.getColor(R.styleable.CrilyView_color, Color.RED);
        typedArray.recycle();
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int hight = MeasureSpec.getSize(heightMeasureSpec);
        int hightMode = MeasureSpec.getMode(heightMeasureSpec);
        int wight = MeasureSpec.getSize(widthMeasureSpec);
        int wightMode =MeasureSpec.getMode(widthMeasureSpec);

        if (hightMode ==MeasureSpec.AT_MOST&&wightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if (hightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(wight,200);
        }else if (widthMeasureSpec==MeasureSpec.AT_MOST){
            setMeasuredDimension(hight,200);
        }



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();
        int height = getWidth() - paddingLeft - paddingRight;
        int wight = getHeight() - paddingBottom - paddingTop;
        int radio = Math.min(height, wight) / 2;

//        canvas.drawText();
        mPaint.setTextSize((int)(wight*0.2));  //根据宽度自定义大小

        canvas.drawText("李清菊===", paddingLeft+wight / 2, paddingTop+wight / 2 ,mPaint);
//        canvas.drawCircle(paddingLeft + wight / 2, paddingTop + wight / 2, radio, mPaint);


    }
}
