package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class LoadingButton extends AppCompatButton {

    GradientDrawable mGradientDrawable;
    AnimatorSet mMorphingAnimatorSet;
    Context mContext;

    public LoadingButton(Context context) {
        super(context);
    }

    public LoadingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context) {
        mGradientDrawable = (GradientDrawable)
                ContextCompat.getDrawable(context, R.drawable.shape_default);

        setBackground(mGradientDrawable);
    }

    /**
     * Method called to start the animation. Morphs in to a ball and then starts a loading spinner.
     */
    public void startAnimation(final Context mContext) {
        /*if(mState != State.IDLE){
            return;
        }*/

        int initialWidth = getWidth();
        int initialHeight = getHeight();

        int initialCornerRadius = 0;
        int finalCornerRadius = 1000;

        /*mState = State.PROGRESS;
        mIsMorphingInProgress = true;*/
        this.setText(null);
        setClickable(false);

        int toWidth = 300; //some random value...
        int toHeight = toWidth; //make it a perfect circle

        ObjectAnimator cornerAnimation =
                ObjectAnimator.ofFloat(mGradientDrawable,
                        "cornerRadius",
                        initialCornerRadius,
                        finalCornerRadius);

        ValueAnimator widthAnimation = ValueAnimator.ofInt(initialWidth, toWidth);
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.width = val;
                setLayoutParams(layoutParams);
            }
        });

        ValueAnimator heightAnimation = ValueAnimator.ofInt(initialHeight, toHeight);
        heightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = val;
                setLayoutParams(layoutParams);
            }
        });

        mMorphingAnimatorSet = new AnimatorSet();
        mMorphingAnimatorSet.setDuration(300);
        mMorphingAnimatorSet.playTogether(cornerAnimation, widthAnimation, heightAnimation);
        mMorphingAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onAnimationEnd(Animator animation) {
                mGradientDrawable = (GradientDrawable)
                        ContextCompat.getDrawable(mContext, R.drawable.round_shape);

                setBackground(mGradientDrawable);
                //mIsMorphingInProgress = false;
            }
        });
        mMorphingAnimatorSet.start();
    }
}
