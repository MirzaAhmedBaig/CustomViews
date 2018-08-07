package org.avantari.dhyana.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import org.avantari.dhyana.R;

/**
 * Created by bala on 27-11-2017.
 */

public class InfinityView extends View {
    int iCurStep = 0;
    boolean firstCompleted = false;
    boolean secondCompleted = false;
    boolean thirdCompleted = false;
    boolean fourthCompleted = false;
    private String TAG = InfinityView.class.getSimpleName();
    private Paint arcPaint;
    private Path arcPath;
    private Paint arcPaint2;
    private Path arcPath2;
    private Paint ovalPaint;
    private Path ovalPath;
    private Paint ovalPaint2;
    private Path ovalPath2;
    private Paint circlePaint;

    public InfinityView(Context context) {
        super(context);
        init();
    }

    public InfinityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InfinityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public InfinityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.BLACK);
        arcPaint.setStrokeWidth(5);
        arcPaint.setShadowLayer(5.0f, 0.0f, 2.0f, 0xFF000000);


        arcPaint2 = new Paint();
        arcPaint2.setStyle(Paint.Style.STROKE);
        arcPaint2.setColor(Color.BLACK);
        arcPaint2.setStrokeWidth(5);
        arcPaint2.setShadowLayer(5.0f, 0.0f, 2.0f, R.color.shadow);


        ovalPaint = new Paint();
        ovalPaint.setColor(Color.BLACK);
        ovalPaint.setStrokeWidth(5);
        ovalPaint.setShadowLayer(5.0f, 0.0f, 2.0f, R.color.shadow);
        ovalPaint.setStyle(Paint.Style.STROKE);

        ovalPaint2 = new Paint();
        ovalPaint2.setColor(Color.BLACK);
        ovalPaint2.setStrokeWidth(5);
        ovalPaint2.setStyle(Paint.Style.STROKE);
        ovalPaint2.setShadowLayer(5.0f, 0.0f, 2.0f, R.color.shadow);


        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.WHITE);
        circlePaint.setShadowLayer(10.0f, 0.0f, 2.0f, R.color.shadow);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float width = (float) getWidth();
        float height = (float) getHeight();

        float widthFactor = width / 4;
        float heightFactor = height / 4;

        ovalPath = new Path();
        ovalPath.moveTo(widthFactor, heightFactor * 3);
        ovalPath.cubicTo(-5, heightFactor * 3, -5, heightFactor, widthFactor, heightFactor);

        ovalPath2 = new Path();
        ovalPath2.moveTo(widthFactor * 3, heightFactor * 3);
        ovalPath2.cubicTo(widthFactor * 4 + 5, heightFactor * 3, widthFactor * 4 + 5, heightFactor, widthFactor * 3, heightFactor);

        arcPath = new Path();
        arcPath.moveTo(widthFactor, heightFactor);

        arcPath.cubicTo(widthFactor * 2, heightFactor, widthFactor * 2, heightFactor * 3, widthFactor * 3, heightFactor * 3);

        arcPath2 = new Path();
        arcPath2.moveTo(widthFactor * 3, heightFactor);

        arcPath2.cubicTo(widthFactor * 2, heightFactor, widthFactor * 2, heightFactor * 3, widthFactor, heightFactor * 3);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(ovalPath, ovalPaint);
        canvas.drawPath(ovalPath2, ovalPaint2);
        canvas.drawPath(arcPath, arcPaint);
        canvas.drawPath(arcPath2, arcPaint2);
        animate(canvas);
    }

    private void animate(Canvas canvas) {
        if (!firstCompleted) {

            PathMeasure pm = new PathMeasure(ovalPath, false);
            float fSegmentLen = pm.getLength() / 100;
            float afP[] = {0f, 0f};
            pm.getPosTan(fSegmentLen * iCurStep, afP, null);
            canvas.drawCircle(afP[0], afP[1], 20, circlePaint);
            if (iCurStep <= 100) {
                iCurStep++;
                invalidate();
            } else {
                iCurStep = 0;
                firstCompleted = true;
                secondCompleted = false;
                invalidate();
            }
        } else if (!secondCompleted) {
            PathMeasure pm2 = new PathMeasure(arcPath, false);
            float fSegmentLen = pm2.getLength() / 100;
            float afP[] = {0f, 0f};
            pm2.getPosTan(fSegmentLen * iCurStep, afP, null);
            canvas.drawCircle(afP[0], afP[1], 20, circlePaint);
            if (iCurStep <= 100) {
                iCurStep++;
                invalidate();
            } else {
                iCurStep = 0;
                secondCompleted = true;
                thirdCompleted = false;
                invalidate();
            }
        } else if (!thirdCompleted) {
            PathMeasure pm3 = new PathMeasure(ovalPath2, false);
            float fSegmentLen = pm3.getLength() / 100;
            float afP[] = {0f, 0f};
            pm3.getPosTan(fSegmentLen * iCurStep, afP, null);
            canvas.drawCircle(afP[0], afP[1], 20, circlePaint);
            if (iCurStep <= 100) {
                iCurStep++;
                invalidate();
            } else {
                iCurStep = 0;
                thirdCompleted = true;
                fourthCompleted = false;
                invalidate();
            }
        } else if (!fourthCompleted) {
            PathMeasure pm4 = new PathMeasure(arcPath2, false);
            float fSegmentLen = pm4.getLength() / 100;
            float afP[] = {0f, 0f};
            pm4.getPosTan(fSegmentLen * iCurStep, afP, null);
            canvas.drawCircle(afP[0], afP[1], 20, circlePaint);
            if (iCurStep <= 100) {
                iCurStep++;
                invalidate();
            } else {
                iCurStep = 0;
                fourthCompleted = true;
                firstCompleted = false;
                invalidate();
            }
        }
    }
}
