package com.example.liqingju.meituancontext;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by liqingju on 15/12/14.
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener {
    private Context mContext;
    private View headView;
    private ImageView imageView;
    private int lastY;
    private FrameLayout mFramelayout;
    private float allHight;
    private int allWight;
    private int haveHight;
    private VelocityTracker mVlocity;
   // ScrollView
    TextView text;


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
        mVlocity =VelocityTracker.obtain();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        allHight = displayMetrics.heightPixels;
        allWight = displayMetrics.widthPixels;
        mFramelayout = new FrameLayout(context);
        mFramelayout.setBackgroundColor(0X000000);
        haveHight = (int) (9f * (allWight / 16.0f));
        Log.e("haveHight== ", haveHight + "  " + allHight);
        LayoutParams layoutParams = new LayoutParams(allWight, haveHight);
        mFramelayout.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParamsImageView = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        imageView = new ImageView(context);
        layoutParamsImageView.gravity = 80;
//        imageView.setLayoutParams(layoutParamsImageView);
        mFramelayout.addView(imageView);

//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        headView = inflater.inflate(R.layout.sample_my_view, null);
//        imageView = (ImageView) headView.findViewById(R.id.hander_image);
        addHeaderView(mFramelayout);
        super.setOnScrollListener(this);
//     addHeaderView( );


    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (lastY == 0) {
                    lastY = (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int Y = (int) ev.getY();
                mVlocity.addMovement(ev);
                mVlocity.computeCurrentVelocity(1000);
                mVlocity.getYVelocity();
                int hight = getFrameLayout().getBottom();
                ViewGroup.LayoutParams layoutParams = mFramelayout.getLayoutParams();
                if (hight >= haveHight) {

                    if (hight <= allHight * 0.75f) {
                        if (getFrameLayout().getHeight() + (Y - lastY) > allHight * 0.75f) {
                            layoutParams.height = (int)(allHight * 0.75f);
                        } else {
                            layoutParams.height = getFrameLayout().getHeight() + (Y - lastY);
                        }


                        getFrameLayout().setLayoutParams(layoutParams);
                        lastY = (int) ev.getY();
                        return true;
                    }

                    if (hight <haveHight) {
                        Log.e("=====","走这样的额");
                        layoutParams.height = this.haveHight;
                        this.getFrameLayout().setLayoutParams(layoutParams);
                        return super.onTouchEvent(ev);

                    }
                    return true;

                }


                lastY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (getY()<0){
                   scrollTo(0,0);
                    getFrameLayout().scrollTo(0,0);
                }
                mVlocity.clear();
                Log.e("BOTTOM", getFrameLayout().getBottom() + "   " + getFrameLayout().getHeight() + "  " + getFrameLayout().getScrollY()+"  "+getY());
                break;


        }
        lastY = (int) ev.getY();

        return super.onTouchEvent(ev);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int f = haveHight - getFrameLayout().getBottom();

        if (f > 0.0f && f < haveHight) {
            double i = (0.65d * f);
            Log.e("huadong", i + "");
            imageView.scrollTo(0, -(int) i);
        } else if (imageView.getScrollY() != 0) {
            Log.e("imageView=11=  ", "" + imageView.getScrollY());
            imageView.scrollTo(0, 0);
        }


        Log.e("imageView==  ", "" + imageView.getScrollY());
    }

    public ImageView getHanderImageView() {

        return imageView;
    }

    public FrameLayout getFrameLayout() {

        return mFramelayout;
    }
}
