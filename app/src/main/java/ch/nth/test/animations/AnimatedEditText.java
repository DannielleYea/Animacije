package ch.nth.test.animations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * @Author Danijel Turić
 * 2019
 * Animations
 */
public class AnimatedEditText extends AppCompatEditText {

    private Paint paint;
    private float factor = 1f;

    public AnimatedEditText(Context context) {
        super(context);

        setup();
    }

    public AnimatedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        setup();
    }

    public AnimatedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setup();
    }

    void setup() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5f);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        if (focused)  {
            this.invalidate();
            factor = .5f;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine((getWidth()) * (0 + factor), getHeight(), (getWidth()) * (1 - factor), getHeight(), paint);
        factor *= 0.9;

        if (factor >= 0.001)
            invalidate();
    }
}
