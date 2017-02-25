package com.example.anikaido.sandbox.ui.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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

            if (dependency instanceof AppBarLayout) {
                final Rect rect = new Rect();

                getDescendantRect(parent, dependency, rect);

                if (rect.bottom <= 300) {
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

        static void getDescendantRect(ViewGroup parent, View descendant, Rect out) {
            out.set(0, 0, descendant.getWidth(), descendant.getHeight());
            offsetDescendantRect(parent, descendant, out);
        }

        public static void offsetDescendantRect(ViewGroup group, View child, Rect rect) {
            ThreadLocal<Matrix> sMatrix = new ThreadLocal<>();
            ThreadLocal<RectF> sRectF = new ThreadLocal<>();

            Matrix m = sMatrix.get();
            if (m == null) {
                m = new Matrix();
                sMatrix.set(m);
            } else {
                m.reset();
            }

            offsetDescendantMatrix(group, child, m);

            RectF rectF = sRectF.get();
            if (rectF == null) {
                rectF = new RectF();
                sRectF.set(rectF);
            }
            rectF.set(rect);
            m.mapRect(rectF);
            rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f),
                    (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
        }

        static void offsetDescendantMatrix(ViewParent target, View view, Matrix m) {
            final ViewParent parent = view.getParent();
            if (parent instanceof View && parent != target) {
                final View vp = (View) parent;
                offsetDescendantMatrix(target, vp, m);
                m.preTranslate(-vp.getScrollX(), -vp.getScrollY());
            }

            m.preTranslate(view.getLeft(), view.getTop());

            if (!view.getMatrix().isIdentity()) {
                m.preConcat(view.getMatrix());
            }
        }
    }
}
