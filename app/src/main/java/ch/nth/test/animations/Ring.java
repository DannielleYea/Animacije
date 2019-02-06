package ch.nth.test.animations;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author Danijel Turić
 * 2019
 * Animations
 */
class Ring extends View {

    private Paint mPaint, backPaint;
    private RectF mOval;

    private float progress = 100;

    public Ring(Context context) {
        super(context);

        init();
    }

    public Ring(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public Ring(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);//1dp
        mPaint.setDither(true);                    // set the dither to true
        mPaint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        mPaint.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
        mPaint.setPathEffect(new PathEffect());   // set the path effect when they join.


        backPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backPaint.setColor(Color.WHITE);
        backPaint.setStyle(Paint.Style.STROKE);
        backPaint.setStrokeWidth(4);//1dp
        backPaint.setDither(true);                    // set the dither to true
        backPaint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        backPaint.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
        backPaint.setPathEffect(new PathEffect());   // set the path effect when they join.

        measure(MeasureSpec.EXACTLY, View.MeasureSpec.EXACTLY);
        mOval = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());

        mOval = new RectF(2, 2, getWidth(), getHeight());
    }

    public void setProgress(float percentage) {
        this.progress = percentage;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mOval.right = getWidth() - 2;
        mOval.bottom = getHeight() - 2;

        canvas.drawArc(mOval, 0, 360, false, backPaint);

        int angle = (int) (progress * 360);
        canvas.drawArc(mOval, -90, angle, false, mPaint);
    }
}