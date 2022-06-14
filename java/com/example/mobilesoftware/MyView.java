package com.example.mobilesoftware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    private Paint paint;
    private Path path;
    private Paint canvasPaint;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    static float line_width = 10f;
    static int line_color = Color.BLACK;
    static int num_btn = 3;
    boolean isemboss = false;
    boolean isblur = false;
    boolean fill_tf = false;

    EmbossMaskFilter eMask = new EmbossMaskFilter(new float[] {3, 3, 10}, 0.5f, 5, 10);
    BlurMaskFilter bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.INNER);

    private float trX, trY, frX, frY, radius;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing(){
        //get drawing area setup for interaction
        path = new Path();
        paint = new Paint();
        paint.setColor(line_color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(line_width);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void allClear(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int prew, int preh) {
        super.onSizeChanged(w, h, prew, preh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        paint.setStyle(Paint.Style.STROKE);

        if (isemboss) {
            paint.setMaskFilter(eMask);
        }
        else if (isblur) {
            paint.setMaskFilter(bMask);
        }
        else {
            paint.setMaskFilter(null);
        }

        if (num_btn == 0) {
            canvas.drawLine(trX, trY, frX, frY, paint);
        }
        else if (num_btn == 1) {
            if (fill_tf) paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(trX, trY, frX, frY, paint);
        }
        else if (num_btn == 2) {
            if (fill_tf) paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(trX, trY, radius, paint);
        }
        else if (num_btn == 3) {
            canvas.drawPath(path, paint);
        }
        else if (num_btn == 4) {
            paint.setMaskFilter(null);
            canvas.drawPath(path, paint);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                trX = eventX;
                trY = eventY;
                if (num_btn == 0) {
                    path.moveTo(eventX, eventY);
                }
                else if (num_btn == 2) {
                    radius = 1;
                }
                else if (num_btn == 3 | num_btn == 4) {
                    path.moveTo(eventX, eventY);
                }

            case MotionEvent.ACTION_MOVE:
                if (num_btn == 0 || num_btn == 1) {
                    frX = eventX;
                    frY = eventY;
                }
                else if (num_btn == 2) {
                    radius = (float) Math.sqrt(Math.pow(eventX - trX, 2) + Math.pow(eventY - trY, 2));
                }
                else if (num_btn == 3 | num_btn == 4) {
                    path.lineTo(eventX, eventY);
                }
                break;
            case MotionEvent.ACTION_UP:
                frX = eventX;
                frY = eventY;
                if (num_btn == 0) {
                    drawCanvas.drawLine(trX, trY, frX, frY, paint);
                }
                else if (num_btn == 1) {
                    drawCanvas.drawRect(trX, trY, frX, frY, paint);
                }
                else if (num_btn == 2) {
                    drawCanvas.drawCircle(trX, trY, radius, paint);
                }
                else if (num_btn == 3 | num_btn == 4) {
                    drawCanvas.drawPath(path, paint);
                }
                path.reset();
                break;

            default:
                return false;
        }
        invalidate();
        return true;
    }

    protected void setColor(int color) {
        invalidate();
        paint.setColor(color);
    }

    protected void setWidth(float width) {
        invalidate();
        paint.setStrokeWidth(width);
    }

}