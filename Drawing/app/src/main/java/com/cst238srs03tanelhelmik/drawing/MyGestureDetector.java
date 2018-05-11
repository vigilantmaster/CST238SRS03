package com.cst238srs03tanelhelmik.drawing;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.media.Image;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

// got from here https://stackoverflow.com/questions/4207067/how-to-implement-touch-listener-on-image
public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener
{
    public static  List<PointF> myList = new ArrayList<PointF>();

    float myX, myY;
    Context myContext;
    DrawingView myDrawingView;


    MyGestureDetector(Context context, DrawingView myDrawingView)
    {
      myContext = context;
      this.myDrawingView = myDrawingView;
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }
    @Override
    public boolean onDoubleTapEvent(MotionEvent e)
    {
        Log.i("Taghere..", "onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        boolean result = super.onSingleTapUp(e);
        myX = e.getX();
        myY = e.getY();
        PointF newPointF = new PointF(myX,myY);
        myList.add(newPointF);
        myDrawingView.invalidate();
        return result;
    }

    // adding a fling listener http://illusionsandroid.blogspot.com/2011/05/adding-fling-gesture-listener-to-view.html
    private final int SWIPE_MIN_DISTANCE = 20;
    private final int SWIPE_THRESHOLD_VELOCITY = 100;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            // Right to left, clear the screen
            myDrawingView.clear();
            return true;
        } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && abs(velocityX) >  SWIPE_THRESHOLD_VELOCITY) {
            // Left to right, change the color
            myDrawingView.ColorChange();
            return true;
        }
        if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            // Bottom to top, show current iteration number

            return true;
        } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            // Top to bottom, show current fraction number

            return true;
        }
        return false;
    }

}
