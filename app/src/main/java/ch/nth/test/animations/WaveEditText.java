package ch.nth.test.animations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * @Author Danijel Turić
 * 2019
 * Animations
 */
public class WaveEditText extends AppCompatEditText {

    private Paint mPaint;
    private Path path;

    private int refreshes = 20;

    private float move = 5;

    public void setup() {

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#FFFFFF"));
        mPaint.setStrokeWidth(5f);


        path = new Path();
    }

    public WaveEditText(Context context) {
        super(context);

        setup();
    }

    public WaveEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        setup();
    }

    public WaveEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setup();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        refreshes = 0;

        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (refreshes < 25) {
            path = new Path();

            for (int i = 0; i < getWidth(); i += 10) {

                float y = (float) Math.sin((i + move) * 50) * (i % 20);

                path.addCircle(i, getHeight() + y - 5, 3f, Path.Direction.CW);
            }

            move += 0.5;

            canvas.drawPath(path, mPaint);

            refreshes++;
            invalidate();
        } else if (refreshes == 25) {
            canvas.drawLine(0, getHeight() - 5, getWidth(), getHeight() - 5, mPaint);
        }
    }
}
