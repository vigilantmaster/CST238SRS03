package com.cst238srs03tanelhelmik.drawing;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MyTextWatcher implements TextWatcher {
    EditText myIterationsText ;
    EditText myFracText ;
    DrawingView myDrawingView;
    public void setMyIterationsText(EditText Iter)
    {
        myIterationsText = Iter;
    }

    public void setMyFracText(EditText Frac)
    {
        myFracText = Frac;
    }

    public void setDrawingView(DrawingView drawingView)
    {
        myDrawingView = drawingView;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (editable != null && !editable.toString().equalsIgnoreCase("")){
            // Checking editable.hashCode() to understand which edittext is using right now
            if (myIterationsText.getText().hashCode() == editable.hashCode()){
                // This is just an example, your magic will be here!
                String value = editable.toString();
                myIterationsText.removeTextChangedListener(this);
                myDrawingView.iterations = Integer.parseInt(value);
                myIterationsText.setText(value);
                myIterationsText.addTextChangedListener(this);
            }
        } else if (myFracText.getText().hashCode() == editable.hashCode()){
            // This is just an example, your magic will be here!
            String value = editable.toString();
            myFracText.removeTextChangedListener(this);
            myFracText.setText(value);
            myDrawingView.frac = Float.parseFloat(value);
            myFracText.addTextChangedListener(this);
        }
    }
}
