package com.example.liqingju.meituancontext;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;

/**
 * Created by liqingju on 16/1/6.
 */
public class MyScrollview extends FrameLayout {
    private OverScroller mOverScroller;
    private Context mContext;
    private VelocityTracker mVelocityTracker;
    private float Intercepty;
    private float lastTouchY;
    private ViewConfiguration mViewCondig;


    public MyScrollview(Context context) {
        super(context);
        init(context);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float y = ev.getY();
        int action = ev.getAction();
        boolean isIntercapt = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                Intercepty = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                if (Math.abs(y - Intercepty) > 5) {
                    isIntercapt = true;
                } else {
                    isIntercapt = false;
                }

                break;

        }
        lastTouchY = ev.getY();
        return isIntercapt;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();

        mVelocityTracker.addMovement(event);
        Log.e("action", "没有辸1111？？");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (!mOverScroller.isFinished()) {
                    mOverScroller.abortAnimation();
                }

                break;
            case MotionEvent.ACTION_MOVE:
//                Log.e("action", "22222222");
                int dey = (int) (y - lastTouchY);
                Log.e("====    ", (getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop())) + "");

                Log.e("action", "   " + dey + "     " + getScrollY() + "    " + getHeight() + getChildAt(0).getHeight());
//                if (Math.abs(dey) > mViewCondig.getScaledTouchSlop()) {
                if ((getScrollY() - dey) >= (getChildAt(0).getHeight() - getHeight())) {
                    Log.e("action", "最底部");
                    scrollTo(0, getChildAt(0).getHeight() - getHeight());
                    break;
                }
                if (getScrollY() - dey <= 0) {
//                    Log.e("action", "最顶部  ");
                    scrollTo(0, 0);
                    break;
                }
                scrollBy(0, -dey);

                break;

            case MotionEvent.ACTION_UP:
                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, mViewCondig.getScaledMaximumFlingVelocity());
                float velocityY = velocityTracker.getYVelocity();
                Log.e("myScrollView11111   ", "" + velocityY);
                if (Math.abs(velocityY) > mViewCondig.getScaledMinimumFlingVelocity()) {
                    Log.e("myScrollView    ", "" + velocityY);
                    mOverScroller.fling(getScrollX(), getScrollY(), 0, (int) -velocityY, 0, 0, 0, getChildAt(0).getHeight() - getHeight());
                    postInvalidateOnAnimation();
                } else if (mOverScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getChildAt(0).getHeight() - getHeight())) {
                    postInvalidateOnAnimation();
                }

                velocityTracker.clear();
                break;
        }

        lastTouchY = event.getY();


        return true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.UNSPECIFIED) {
            return;
        }


        if (getChildCount() > 0) {
            final View child = getChildAt(0);

            int height = getMeasuredHeight();
            int chileH = child.getMeasuredHeight();
            Log.e("======    ", height + "   " + chileH);
            //如果大小没有 父VIEW大 就设置父VIEW 的大小
            if (chileH < height) {
                final FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();

                int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                        getPaddingLeft() + getPaddingRight(), lp.width);
                height -= getPaddingTop();
                height -= getPaddingBottom();
                int childHeightMeasureSpec =
                        MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }

    }


    //设置子view的大小 要多大给多大
    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
                getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin
                        + widthUsed, lp.width);
        final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                lp.topMargin + lp.bottomMargin, MeasureSpec.UNSPECIFIED);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }


    private void init(Context context) {
        this.mContext = context;
        mOverScroller = new OverScroller(mContext);
        mVelocityTracker = VelocityTracker.obtain();
        mViewCondig = ViewConfiguration.get(mContext);
    }

    private void smoothScrollBy(int dy) {
        mOverScroller.startScroll(0, getScrollY(), 0, dy, 500);
    }

    @Override
    public void computeScroll() {
        if (mOverScroller.computeScrollOffset()) {
            scrollTo(mOverScroller.getCurrX(), mOverScroller.getCurrY());
            postInvalidate();
        }


    }
}
