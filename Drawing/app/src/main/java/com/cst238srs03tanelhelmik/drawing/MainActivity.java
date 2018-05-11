package com.cst238srs03tanelhelmik.drawing;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public DrawingView myImageView;
    private  GestureDetector gdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImageView = findViewById(R.id.imageViewMain);
        gdt = new GestureDetector(new MyGestureDetector(this, myImageView));
        myImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                return gdt.onTouchEvent(event);
            }
        });
    }

    /** Called when the user taps the Send button */
    public void showInfo(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}
