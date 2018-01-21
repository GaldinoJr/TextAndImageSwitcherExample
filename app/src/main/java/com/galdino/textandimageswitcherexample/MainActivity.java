package com.galdino.textandimageswitcherexample;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.galdino.textandimageswitcherexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private Animation mAnimationInRight;
    private Animation mAnimationOutRight;
    private Animation mAnimationOutLeft;
    private Animation mAnimationInLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mBinding.tvSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // Estilizar o textView
                TextView myText = new TextView(MainActivity.this);
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(36);
                myText.setTextColor(Color.BLUE);
                return myText;
            }
        });

        mBinding.ivSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new ImageView(MainActivity.this);
            }
        });

        // Declare the in and out animations and initialize them
        mAnimationInRight = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        mAnimationOutRight = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        mAnimationInLeft = AnimationUtils.loadAnimation(this,R.anim.slide_in_right);
        mAnimationOutLeft = AnimationUtils.loadAnimation(this,R.anim.slide_out_left);
        // set the animation type of textSwitcher
        mBinding.tvSwitcher.setCurrentText(getString(R.string.first_message));
        mBinding.ivSwitcher.setImageResource(R.drawable.background_goku);
        //
        mBinding.btNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                setAnimationToRight();
                // troca o texto
                mBinding.tvSwitcher.setText(getString(R.string.second_message));
                // troca a foto
               changePicture(R.drawable.background_vegeta);
//                mBinding.ivSwitcher.setImageResource(R.drawable.background_vegeta);
            }
        });
        mBinding.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnimationToLeft();
                mBinding.tvSwitcher.setText(getString(R.string.first_message));
                changePicture(R.drawable.background_goku);
            }
        });
    }

    private void changePicture(final int background_img) {
        mBinding.ivSwitcher.setImageResource(background_img);
        // TODO PARA IMAGENS GRANDES, NÃO CONSEGUI FAZER FUNCIONAR O MÉTODO onResourceReady
//        Glide.with(MainActivity.this)
//                .load(background_img)
//
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        mBinding.ivSwitcher.setImageResource(new BitmapDrawable(resources, ((BitmapDrawable)resource).getBitmap()));
//                        return true;
//                    }
//                }).into((ImageView)mBinding.ivSwitcher.getCurrentView());
    }


    private void setAnimationToLeft()
    {
        mBinding.tvSwitcher.setInAnimation(mAnimationInLeft);
        mBinding.tvSwitcher.setOutAnimation(mAnimationOutLeft);
        mBinding.ivSwitcher.setInAnimation(mAnimationInLeft);
        mBinding.ivSwitcher.setOutAnimation(mAnimationOutLeft);
    }

    private void setAnimationToRight() {
        mBinding.tvSwitcher.setInAnimation(mAnimationInRight);
        mBinding.tvSwitcher.setOutAnimation(mAnimationOutRight);
        mBinding.ivSwitcher.setInAnimation(mAnimationInRight);
        mBinding.ivSwitcher.setOutAnimation(mAnimationOutRight);
    }
}
