package com.example.rahul.animationlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {


    /*  ImageView mTextView;
      boolean isBo;
      private Animation mAnimation;*/
    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*mTextView = (ImageView) findViewById(R.id.animate_view);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.swinging);*/


        lottieAnimationView = (LottieAnimationView) findViewById(R.id.ray_orange_view);
        lottieAnimationView.setImageAssetsFolder("images/");
        lottieAnimationView.setAnimation("rahul4.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        /*TranslateAnimation anim = new TranslateAnimation(0f, 1000f, 0f, 0f);  // might need to review the docs
        anim.setDuration(5000); // set how long you want the animation

        mTextView.setAnimation(anim);
        mTextView.setVisibility(View.VISIBLE);

        mTextView.startAnimation(anim);*/


    }

    public void startAnimation(View view) {
        // lottieAnimationView.playAnimation();
        // mTextView.startAnimation(mAnimation);
        /*if (!isBo) {
            isBo = true;
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(mTextView, "x", 1000);
            objectanimator.setDuration(4000);
            objectanimator.start();
        } else {
            isBo = false;
            ObjectAnimator objectanimatortwo = ObjectAnimator.ofFloat(mTextView, "x", -1000);
            objectanimatortwo.setDuration(4000);
            objectanimatortwo.start();
        }*/


    }
}
