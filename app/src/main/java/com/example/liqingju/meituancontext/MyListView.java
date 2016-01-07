package com.example.liqingju.meituancontext;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

public class MyListView extends ListView implements OnScrollListener {
    private Context mContext;
    private int hight;
    private int wight;
    private int havaHight;
    private FrameLayout frameLayout;
    private ImageView mImageView;
    private float mListY;
    private ScalingRunnalable runAntion = new ScalingRunnalable();
    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float paramAnonymousFloat) {
            System.out.println("paramAnonymousFloat==  " + paramAnonymousFloat);
            float f = paramAnonymousFloat - 1.0F;
            return 1.0F + f * (f * (f * (f * f)));
        }
    };

    // private float

    public MyListView(Context context) {
        super(context);
        initData(context);

    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData(context);
    }

    private void initData(Context context) {
        this.mContext = context;

        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        hight = metrics.heightPixels;
        wight = metrics.widthPixels;
        frameLayout = new FrameLayout(mContext);
        havaHight = (int) (9f * (wight / 16.0f));
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(wight, havaHight);
        frameLayout.setLayoutParams(layoutParams);
        mImageView = new ImageView(mContext);
        frameLayout.addView(mImageView);
        addHeaderView(frameLayout);
        super.setOnScrollListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: // 点击
                if (mListY == 0) {
                    mListY = ev.getY();
                }

                break;
            case MotionEvent.ACTION_MOVE: // 移动
                float Y = ev.getY();
                int frameButtom = frameLayout.getBottom();
                android.view.ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
                if (frameButtom >= havaHight && frameButtom <= (hight * 0.75)) {

                    if (frameButtom <= (hight * 0.75)) {
                        layoutParams.height = frameLayout.getHeight() + (int) (Y - mListY);
                        frameLayout.setLayoutParams(layoutParams);
                        mListY = Y;
                        return true;
                    }
                    if (frameButtom <= havaHight) {
                        layoutParams.height = havaHight;
                        frameLayout.setLayoutParams(layoutParams);
                        mListY = Y;
                        return super.onTouchEvent(ev);
                    }

                }
                if (frameButtom <= havaHight) {
                    layoutParams.height = havaHight;
                    frameLayout.setLayoutParams(layoutParams);
                    mListY = Y;
                    return super.onTouchEvent(ev);
                }

                break;
            case MotionEvent.ACTION_UP: // 离开
                mListY = 0;
                runAntion.startAnimation(200L);
                break;

            default:
                break;
        }
        mListY = ev.getY();
        return super.onTouchEvent(ev);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        float f = havaHight - frameLayout.getBottom();
        if (f > 0.0 && f <= havaHight) {
            int dex = (int) (0.65d * f);
            mImageView.scrollTo(0, -dex);
            System.out.println("走这里====  " + dex + "   " + f);
        } else if (mImageView.getScrollY() != 0) {
            mImageView.scrollTo(0, 0);

        }

    }

    public ImageView getImageView() {

        return mImageView;
    }

    class ScalingRunnalable implements Runnable {
        long mDuration;
        boolean mIsFinished = true;
        float mScale;
        long mStartTime;

        ScalingRunnalable() {
        }

        public void abortAnimation() {
            this.mIsFinished = true;
        }

        public boolean isFinished() {
            return this.mIsFinished;
        }

        public void run() {
            float f2;
            ViewGroup.LayoutParams localLayoutParams;
            if ((!this.mIsFinished) && (this.mScale > 1.0D)) {
                float f1 = ((float) SystemClock.currentThreadTimeMillis() - (float) this.mStartTime) / (float) this.mDuration;
                f2 = this.mScale - (this.mScale - 1.0F) * sInterpolator.getInterpolation(f1);
                localLayoutParams = frameLayout.getLayoutParams();
                if (f2 > 1.0F) {
                    Log.d("mmm", "f2>1.0");
                    localLayoutParams.height = ((int) (f2 * havaHight));
                    frameLayout.setLayoutParams(localLayoutParams);
                    post(this);
                    return;
                }
                this.mIsFinished = true;
            }
        }

        public void startAnimation(long paramLong) {
            this.mStartTime = SystemClock.currentThreadTimeMillis();
            this.mDuration = paramLong;
            this.mScale = ((float) (frameLayout.getBottom()) / havaHight);
            this.mIsFinished = false;
            post(this);
        }
    }

}
