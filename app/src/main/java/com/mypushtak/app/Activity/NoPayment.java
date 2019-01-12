package com.mypushtak.app.Activity;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.mypushtak.app.R;

public class NoPayment extends AppCompatActivity {

    LottieAnimationView animationView;
//    TextView donationreqsid;
    TextView donorid_textView;

    int donor_id;
//    int donation_reqs_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_payment);

        animationView=findViewById(R.id.lottieAnimationView);
//        donationreqsid=findViewById(R.id.donationreqsid);
        donorid_textView=findViewById(R.id.donorid_textView);

        donor_id=getIntent().getIntExtra("donorid",0);

//        donationreqsid.setText(donation_reqs_id);
        donorid_textView.setText(String.valueOf(donor_id));

        startCheckAnimation();
    }

    private void startCheckAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animationView.setProgress((Float) valueAnimator.getAnimatedValue());
            }
        });

        if (animationView.getProgress() == 0f) {
            animator.start();
        } else {
            animationView.setProgress(0f);
        }
    }
}
