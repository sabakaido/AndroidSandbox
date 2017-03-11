package com.example.anikaido.sandbox.ui.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by anikaido on 2017/02/25.
 */
@CoordinatorLayout.DefaultBehavior(MyCircleImageView.Behavior.class)
public class MyCircleImageView extends CircleImageView {
    public MyCircleImageView(Context context) {
        super(context);
    }

    public MyCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public static class Behavior extends CoordinatorLayout.Behavior<MyCircleImageView> {
        @Override
        public boolean layoutDependsOn(CoordinatorLayout parent, MyCircleImageView child, View dependency) {
            // 依存がAppBarLayoutの場合
            if (dependency instanceof AppBarLayout) {
                if (dependency.getBottom() <= 300) {
                    child.animate()
                            .scaleX(0f)
                            .scaleY(0f)
                            .alpha(0f)
                            .setDuration(200)
                            .setInterpolator(new FastOutLinearInInterpolator());
                } else {
                    child.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .alpha(1f)
                            .setDuration(200)
                            .setInterpolator(new LinearOutSlowInInterpolator());
                }

                return true;
            }

            return false;
        }

        @Override
        public boolean onDependentViewChanged(CoordinatorLayout parent, MyCircleImageView child, View dependency) {
            return super.onDependentViewChanged(parent, child, dependency);
        }
    }
}
