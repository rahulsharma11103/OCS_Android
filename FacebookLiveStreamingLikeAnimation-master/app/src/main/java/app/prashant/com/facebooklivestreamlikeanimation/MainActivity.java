package app.prashant.com.facebooklivestreamlikeanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    DrawPath drawPath;
    RelativeLayout rLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawPath = new DrawPath(this);

        setContentView(R.layout.activity_main);

        rLayout = (RelativeLayout) findViewById(R.id.rl);
        rLayout.setBackgroundColor(Color.GRAY);
        createPlayBtn();

        addPathLayout();

    }

    private void addPathLayout() {
        RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        newParams.addRule(RelativeLayout.ABOVE, 1);
        // newParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        // drawPath.setPadding(0, 50, 0, 10);
        drawPath.setLayoutParams(newParams);
        rLayout.addView(drawPath);
    }

    /**
     * created play button and set listener to play animation
     */
    @SuppressLint("ResourceType")
    private void createPlayBtn() {

        Button playBtn = new Button(this);
        playBtn.setText("Play Animation");
        playBtn.setTextColor(Color.WHITE);
        playBtn.setId(1);
        playBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        playBtn.setPadding(10, 10, 10, 10);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        playBtn.setLayoutParams(rl);
        rLayout.addView(playBtn);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMultpileView();
            }
        });
    }

    /**
     * add multiple views
     */
    private void addMultpileView() {
        createImageView(drawPath, 0);
    }

    /**
     * create ImageView and set Value animator to play animation on each image
     *
     * @param drawPath
     * @param i
     */
    @SuppressLint("ResourceType")
    private void createImageView(final DrawPath drawPath, int i) {
/*
        final RelativeLayout.LayoutParams forMainRelativeParam = new RelativeLayout.LayoutParams(
                getRandomNumer(), getRandomNumer());

        final RelativeLayout mainRelativeLayout = new RelativeLayout(this);
        mainRelativeLayout.setLayoutParams(forMainRelativeParam);

        drawPath.addView(mainRelativeLayout);*/


        final ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getRandomNumer(), getRandomNumer());
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.thumbs_up);
        imageView.setId(12);
        drawPath.addView(imageView);

/*
        final ImageView imageView1 = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(getRandomNumer(), getRandomNumer());

        imageView1.setLayoutParams(layoutParams);
        imageView1.setImageResource(R.drawable.heart);

        layoutParams1.addRule(RelativeLayout.BELOW, 12);

        mainRelativeLayout.addView(imageView1);*/

        ValueAnimator pathAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        pathAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            float[] point = new float[2];
            // float[] point1 = new float[2];


            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                long duration = animation.getDuration();
                float val = animation.getAnimatedFraction();
                PathMeasure pathMeasure = new PathMeasure(drawPath.getPath(), true);
                pathMeasure.getPosTan(pathMeasure.getLength() * val, point, null);
                //  pathMeasure.getPosTan(pathMeasure.getLength() * val * 2, point1, null);


                imageView.setX(point[0]);
                imageView.setY(point[1]);

                /*imageView1.setX(point1[0]);
                imageView1.setY(point1[1]);*/


                if (val > 0.5) {  // > 0.6 - to stop animation back to reverse state
                    if (drawPath != null) {
                        drawPath.removeView(imageView);

                    }
                }
            }
        });
        pathAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (drawPath != null) {
                    drawPath.removeView(imageView);

                }
            }
        });
        pathAnimator.setDuration(7000 + getRandomDuration());
        pathAnimator.start();
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public class DrawPath extends RelativeLayout {

        public Path mPath;
        public Paint mPaint;

        public DrawPath(Context context) {
            super(context);
            intialisePaint();
        }

        private void intialisePaint() {
            mPaint = new Paint();
            mPaint.setColor(Color.TRANSPARENT);
        }

        /**
         * return Path
         *
         * @return
         */
        private Path getPath() {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            float width = metrics.widthPixels;
            float height = metrics.heightPixels;

            float cp25 = height * 25 / 100;  // 25 percent of width
            float cp50 = height * 50 / 100;  // 50 percent of width
            float cp75 = height * 75 / 100;  // 75 percent of width

            mPath = new Path();

            mPath.moveTo(width/2, convertDpToPx(MainActivity.this,354));
           /* float X3 = cp50;
            float Y3 = height;

            float randomYShift = cp50 + getRandomYValue() * cp75;*/


            float X1 = width / 2;
            float Y1 = 0;


          /*  float X2 = cp75 + randomYShift;
            float Y2 = cp50;*/

           // mPath.lineTo(X1, Y1);

            mPath.cubicTo(X1*75/100, 0, X1*50/100, 0, X1, Y1);75

            // mPath.cubicTo(X1, Y1, X2, Y2, X3, Y3);
            return mPath;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawPath(getPath(), mPaint);
        }
    }

    /**
     * get Random Number for image width and height
     *
     * @return
     */
    public int getRandomNumer() {
        Random random = new Random();
        int randomNumber = random.nextInt(90 - 60) + 60;
        return randomNumber;
    }

    /**
     * get random duration between 2000 to 100
     *
     * @return
     */
    public int getRandomDuration() {
        Random random = new Random();
        int randomNumber = random.nextInt(2000 - 100) + 100;
        return randomNumber;
    }

    /**
     * get random value between 1.0 to 0.1
     *
     * @return
     */
    public float getRandomYValue() {
        Random random = new Random();
        float randomNumber = random.nextFloat() * (1.0f - 0.1f) + 0.1f;
        Log.d(TAG, "getRandomYValue: " + randomNumber);
        return randomNumber;
    }
}
