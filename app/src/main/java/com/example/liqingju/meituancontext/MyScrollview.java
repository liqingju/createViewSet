package com.example.liqingju.meituancontext;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;

/**
 * Created by liqingju on 16/1/6.
 */
public class MyScrollview extends LinearLayout {
    private OverScroller mOverScroller;
    private Context mContext;
    private VelocityTracker mVelocityTracker;
    private float Intercepty;
    private float lastTouchY;
    private ViewConfiguration mViewCondig;
//    HorizontalScrollView


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
                Log.e("action", "22222222");
                int dey = (int) (y - lastTouchY);
                Log.e("action", "   "+dey+"     "+getScrollY()+"    "+getHeight());
//                if (Math.abs(dey) > mViewCondig.getScaledTouchSlop()) {
                    if ((getScrollY() - dey) >= getHeight()) {
                        Log.e("action","最底部");
                        scrollTo(0, getHeight());
                        break;
                    }
                    if (getScrollY() - dey <= 0) {
                        Log.e("action","最顶部  ");
                        scrollTo(0, 0);
                        break;
                    }
                    scrollBy(0, -dey);

//                Log.e("canOverscroll=== ", canOverscroll + "  ");
//                if (Math.abs(dey) > mViewCondig.getScaledTouchSlop()) {

//                    if (overScrollBy(0, dey, 0, getScrollY(), 0, 0, 0, mViewCondig.getScaledOverscrollDistance(), true)) {
//                      mVelocityTracker.clear();
//                    }


//                    OverScrscrollBy(0, -dey);
//                }

//                if (getScrollY() + dey > getHeight()) {
//                    scrollTo(0, getHeight());
//                } else if (getScrollY() + dey < 0) {
//                    scrollBy(0, 0);
//                } else {
//                scrollBy(0, -dey);
//                }
                break;

            case MotionEvent.ACTION_UP:
                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, mViewCondig.getScaledMaximumFlingVelocity());
                float velocityY = velocityTracker.getYVelocity();
                Log.e("myScrollView11111   ", "" + velocityY);
                if (Math.abs(velocityY) > mViewCondig.getScaledMinimumFlingVelocity()) {
                    Log.e("myScrollView    ", "" + velocityY);
                    mOverScroller.fling(getScrollX(), getScrollY(), 0, (int) -velocityY, 0, 0, 0, getHeight());
                    postInvalidateOnAnimation();
                } else if (mOverScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getHeight())) {
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
        int h = getMeasuredHeight();
//        if (getChildCount() > 0) {
//            View view = getChildAt(0);
//            final FrameLayout.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
//            h = h - view.getPaddingTop() - view.getPaddingBottom();
//            int childMeasureH = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
//
//            int childMeasureW = getChildMeasureSpec(widthMeasureSpec, view.getPaddingLeft() + view.getPaddingRight(), layoutParams.width);
//            view.measure(childMeasureW, childMeasureH);
//
//
//        }


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
