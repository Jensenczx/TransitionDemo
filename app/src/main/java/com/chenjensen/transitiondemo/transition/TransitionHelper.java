package com.chenjensen.transitiondemo.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by chenjensen on 16/8/24.
 */
public class TransitionHelper {

    private static final int TIME = 400;
    private static final TimeInterpolator DEFAULT_INTERPOLATOR = new DefaultInterpolator();

    private int mTime = -1;

    private View mPreView;
    private int mPreViewWidth;
    private int mPreViewHeight;
    private int mPreViewX;
    private int mPreViewY;

    private View mNextView;
    private int mNextViewWidth;
    private int mNextViewHeight;
    private int mNextViewX;
    private int mNextViewY;

    private float mScaleWidth;
    private float mScaleHeight;
    private int mTranslationX;
    private int mTranslationY;

    private TimeInterpolator mTimeInterpolator;
    private Activity mActivity;


    /**
     * TranslationX，Y的值表示的是设置的View距离其顶部的移动位置
     */
    public TransitionHelper(){

    }

    public void setNextView(View view) {
        mNextView = view;
    }

    public void setPreView(View view) {
        mPreView = view;
    }

    public void setTime(int time){
        mTime = time;
    }

    public void setTimeInterpolator(TimeInterpolator timeInterpolator) {
        mTimeInterpolator = timeInterpolator;
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    private void inflatePreViewInfo(){
        int[] locations = new int[2];
        mPreView.getLocationOnScreen(locations);
        mPreViewX = locations[0];
        mPreViewY = locations[1];
        mPreViewWidth = mPreView.getWidth();
        mPreViewHeight = mPreView.getHeight();
    }

    private void inflateNextViewInfo(){
        int[] locations = new int[2];
        mNextView.getLocationOnScreen(locations);
        mNextViewX = locations[0];
        mNextViewY = locations[1];
        mNextViewWidth = mNextView.getWidth();
        mNextViewHeight = mNextView.getHeight();
    }

    public void startEnterAnimation() {
        mNextView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                inflatePreViewInfo();
                inflateNextViewInfo();

                mNextView.getViewTreeObserver().removeOnPreDrawListener(this);

                //cal the scale
                mScaleWidth = mPreViewWidth / (float) mNextViewWidth;
                mScaleHeight = mPreViewHeight / (float) mNextViewHeight;

                //由于其存在一个放大效果，所以在计算偏移值的时候，要减去放大后增加的值
                mTranslationX = mNextViewX - mPreViewX + (mNextViewWidth - mPreViewWidth) / 2;
                mTranslationY = mNextViewY - mPreViewY + (mNextViewHeight - mPreViewHeight) / 2;

                mNextView.setScaleX(mScaleWidth);
                mNextView.setScaleY(mScaleHeight);

                mNextView.setTranslationX(-mTranslationX);
                mNextView.setTranslationY(-mTranslationY);

                mNextView.animate()
                        .scaleX(1)
                        .scaleY(1)
                        .translationX(0)
                        .translationY(0)
                        .setDuration(mTime == -1 ? TIME : mTime)
                        .start();
                return true;
            }
        });
    }

    public void exitAnimation(){
        mNextView.animate()
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if(mActivity != null)
                            mActivity.finish();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .scaleX(mScaleWidth)
                .scaleY(mScaleHeight)
                .translationX(-mTranslationX)
                .translationY(-mTranslationY)
                .setDuration(mTime == -1 ? TIME : mTime)
                .start();
    }

}
