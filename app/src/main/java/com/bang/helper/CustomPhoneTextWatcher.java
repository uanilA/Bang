package com.bang.helper;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

public class CustomPhoneTextWatcher implements TextWatcher {

    private final EditText editText;
    private String previousString;

    public CustomPhoneTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        // if the previous editable ends with a dash and new is shorter than previous
        // additionally remove preceding character
        if (previousString != null && previousString.endsWith("-") && editable.toString().length() < previousString.length()) {
            previousString = editable.toString();
            String removedCharacterPriorToDash = editable.toString().substring(0, editable.length() - 1);
            editText.setText(removedCharacterPriorToDash);
            int position = editText.length();
            Editable etext = editText.getText();
            Selection.setSelection(etext, position);
        } else {
            previousString = editable.toString();
            String numericString = Utils.removeNonnumeric(editable.toString());
            int stringLength = numericString.length();
            boolean startsWithOne = numericString.startsWith("1");
            numericString = numericString.substring(0, Math.min(stringLength, 10 + (startsWithOne ? 1 : 0)));

            int lastHyphenIndex = 6 + (startsWithOne ? 1 : 0);
            int secondToLastHyphenIndex = 3 + (startsWithOne ? 1 : 0);

            if (stringLength >= lastHyphenIndex) {
                numericString = numericString.substring(0, lastHyphenIndex) + "-" + numericString.substring(lastHyphenIndex, numericString.length());
            }
            if (stringLength >= secondToLastHyphenIndex) {
                numericString = numericString.substring(0, secondToLastHyphenIndex) + "-" + numericString.substring(secondToLastHyphenIndex, numericString.length());
            }
            if (numericString.startsWith("1")) {
                numericString = numericString.substring(0, 1) + "-" + numericString.substring(1, numericString.length());
            }
            if (!numericString.equals(editable.toString())) {
                editText.setText(numericString);
                int position = editText.length();
                Editable etext = editText.getText();
                Selection.setSelection(etext, position);
            }
        }
    }


}