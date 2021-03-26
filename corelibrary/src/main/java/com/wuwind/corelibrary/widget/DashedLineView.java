package com.wuwind.corelibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import com.wuwind.corelibrary.utils.LogUtil;


/**
 * Created by Wuhf on 2016/5/16.
 * Description ：虚线
 * <com.wuwind.corelibrary.widget.DashedLineView
 * android:layout_width="match_parent"
 * android:layout_height="1dp" />
 */
public class DashedLineView extends View {


    public DashedLineView(Context context) {
        super(context);
    }

    int measuredHeight;
    int measuredWidth;

    private void init() {

        measuredHeight = getMeasuredHeight();
        measuredWidth = getMeasuredWidth();
        LogUtil.e(measuredHeight + "  " + measuredWidth);
    }

    public DashedLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.DKGRAY);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(measuredWidth, measuredHeight);

        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        init();
    }
}
