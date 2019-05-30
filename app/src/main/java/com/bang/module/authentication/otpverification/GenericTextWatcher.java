package com.bang.module.authentication.otpverification;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.bang.R;

public class GenericTextWatcher implements TextWatcher
{
    private EditText editText;
    private EditText editTextNext;
    private EditText editTextPrev;

     GenericTextWatcher(EditText editText, EditText editTextNext, EditText editTextPrev) {
        this.editText = editText;
        this.editTextNext = editTextNext;
        this.editTextPrev = editTextPrev;
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // TODO Auto-generated method stub
        String text = editable.toString();
        switch(editText.getId()) {
            case R.id.etOtpOne:
                if(text.length()==1)
                    editTextNext.requestFocus();
                break;
            case R.id.etOtpTwo:
                if(text.length()==1)
                    editTextNext.requestFocus();
                else if(text.length()==0)
                    editTextPrev.requestFocus();
                break;
            case R.id.etOtpThree:
                if(text.length()==1)
                    editTextNext.requestFocus();
                else if(text.length()==0)
                    editTextPrev.requestFocus();
                break;
            case R.id.etOtpFour:
                if(text.length()==0)
                    editTextPrev.requestFocus();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }
}
