package com.agoodwater.system.view;

import android.content.Context;
import android.util.AttributeSet;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;


/**
 * Created by shiqiang on 2016/10/26.
 */
public class MyMaterialCalendarView extends MaterialCalendarView {
    public MyMaterialCalendarView(Context context) {
        super(context);
    }

    public MyMaterialCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int measureTileSize = MeasureSpec.getSize(heightMeasureSpec);
        int viewTileHieght = MeasureSpec.getSize(widthMeasureSpec);




        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                (int)(measureTileSize*0.8),
                MeasureSpec.EXACTLY
        );

//        int measuredHeight = (int)(measureTileSize*0.7) * viewTileHieght;
        super.onMeasure(viewTileHieght , childHeightMeasureSpec);

    }
}
