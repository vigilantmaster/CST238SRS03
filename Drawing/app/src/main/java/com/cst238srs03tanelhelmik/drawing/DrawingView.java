package com.cst238srs03tanelhelmik.drawing;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v7.widget.AppCompatImageView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.Random;



//https://medium.com/mindorks/building-a-customview-tictactoe-6afa054df928

public class DrawingView extends AppCompatImageView {

    ArrayList<PointF> list = new ArrayList<>();
    Paint mPaint;
    static int color = 0;
    ArrayList<Integer> myColorList = new ArrayList<>();
    int iterations = 50;
    float frac = (float) 0.5;
    EditText myIterationsText;
    EditText myFracText;
    public static ArrayList<PointF> myCalculatedList = new ArrayList<>();

    public DrawingView(Context context) {
        super(context);
        init();

    }
    public DrawingView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }
//constructors..

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        list.add(new PointF(x,y));
        invalidate();
        return true;
    }
    public void onDraw(Canvas c) {
        super.onDraw(c);
        generatePoints();
        if( null != myIterationsText)
        {
            myIterationsText.setText(iterations);
        }
        if( null != myFracText)
        {
            myFracText.setText(Float.toString(frac));
        }

        for(PointF p : list) {
            c.drawPoint(p.x, p.y, mPaint);
        }
        for(PointF pCalc : myCalculatedList) {
            c.drawPoint(pCalc.x, pCalc.y, mPaint);
        }
    }
    private void init()
    {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        myColorList.add(Color.BLUE);
        myColorList.add(Color.RED);
        myColorList.add(Color.GREEN);
        myColorList.add(Color.BLACK);
        myColorList.add(Color.YELLOW);
        myColorList.add(Color.BLACK);
        myIterationsText = findViewById(R.id.textNumberIter);
        myFracText = findViewById(R.id.textDecimalFrac);
        SetTextListeners();
    }
    public void SetTextListeners() {
        MyTextWatcher textWatcher = new MyTextWatcher();
        textWatcher.setMyIterationsText(myIterationsText);
        textWatcher.setMyFracText(myFracText);
        textWatcher.setDrawingView(this);
        if (null != myFracText) {
            myFracText.addTextChangedListener(textWatcher);
        }
        if (null != myFracText) {
            myIterationsText.addTextChangedListener(textWatcher);
        }
    }
    public void clear()
    {
        if (!list.isEmpty())
        {
            list.clear();
            myCalculatedList.clear();
        }
    }
    public void ColorChange()
    {
        Integer mColor = myColorList.get(color++);
        mPaint.setColor(mColor);
        if(color==5) {
            color = 0;
        }
    }
    public void generatePoints() {
        if (list.size() > 2) {
            // Random https://stackoverflow.com/questions/21049747/how-can-i-generate-random-number-in-range-in-android-and-show-it-in-a-textview-f
            int min = 0;
            int max = list.size()-1;
            Random r = new Random();
            int random;
            PointF nextPoint;
            PointF startingPoint;
            random = r.nextInt((max - min) + 1) + min;
            startingPoint = list.get(random);
            PointF newPoint = new PointF();

            for (int i = 0; i < iterations; i++) {
                random = r.nextInt((max - min) + 1) + min;
                nextPoint = list.get(random);

                while (startingPoint == nextPoint) {
                    random = r.nextInt((max - min) + 1) + min;
                    nextPoint = list.get(random);
                }

                newPoint.x = startingPoint.x + ((startingPoint.x - nextPoint.x) * frac);
                newPoint.y = startingPoint.y + ((startingPoint.y - nextPoint.y) * frac);
                myCalculatedList.add(new PointF(newPoint.x, newPoint.y));
                startingPoint = newPoint;
            }
        }
    }
}