package ch.nth.test.animations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author Danijel Turić
 * 2019
 * Animations
 */
public class LoadingView extends View {

    private Paint red;
    private Paint blue;
    private Paint green;
    private float radiusRed = 1f;
    private float radiusBlue = 0f;
    private float radiusGreen = 0f;
    private float radiusIncreaseRed = 0f;
    private float radiusIncreaseBlue = 0f;
    private float radiusIncreaseGreen = 0f;
    private boolean continueAnimation = true;
    private float increaseFactor = .025f;

    public LoadingView(Context context) {
        super(context);

        setup();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setup();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setup();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        setup();
    }

    void setup() {
        red = new Paint();
        blue = new Paint();
        green = new Paint();

//        red.setColor(Color.parseColor("#ff6666"));
//        blue.setColor(Color.parseColor("#6666ff"));
//        green.setColor(Color.parseColor("#29a329"));

        red.setColor(Color.parseColor("#696969"));
        blue.setColor(Color.parseColor("#808080"));
        green.setColor(Color.parseColor("#A9A9A9"));
    }

    void stopAnimation() {
        continueAnimation = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (radiusRed >= 1) {
            radiusIncreaseRed = -increaseFactor;
            radiusIncreaseBlue = increaseFactor;
            radiusIncreaseGreen = 0f;
        } else if (radiusBlue >= 1) {
            radiusIncreaseRed = 0f;
            radiusIncreaseBlue = -increaseFactor;
            radiusIncreaseGreen = increaseFactor;
        } else if (radiusGreen >= 1) {
            radiusIncreaseRed = increaseFactor;
            radiusIncreaseBlue = 0f;
            radiusIncreaseGreen = -increaseFactor;
        }

        radiusRed += radiusIncreaseRed;
        radiusBlue += radiusIncreaseBlue;
        radiusGreen += radiusIncreaseGreen;

        if (radiusRed > radiusBlue && radiusRed > radiusGreen) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getHeight() / 2) * radiusBlue, blue);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getHeight() / 2) * radiusGreen, green);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getHeight() / 2) * radiusRed, red);
        } else if (radiusBlue > radiusRed && radiusBlue > radiusGreen) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getHeight() / 2) * radiusRed, red);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getHeight() / 2) * radiusGreen, green);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getHeight() / 2) * radiusBlue, blue);
        } else if (radiusGreen > radiusBlue && radiusGreen > radiusRed) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getHeight() / 2) * radiusRed, red);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getHeight() / 2) * radiusBlue, blue);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getHeight() / 2) * radiusGreen, green);
        }

        if (continueAnimation) {
            invalidate();
        }
    }
}
