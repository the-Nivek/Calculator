package de.indie_development.calculator.listener.buttons;

import android.view.View;
import android.view.View.OnClickListener;

import de.indie_development.calculator.R;

import static de.indie_development.calculator.activities.MainActivity.vibrator;
import static de.indie_development.calculator.util.Values.bigEditText;
import static de.indie_development.calculator.util.Values.smallEditText;
import static de.indie_development.calculator.util.Values.sp;

public class MemoryButtons implements OnClickListener {

    @Override
    public void onClick(View view) {

        if ( sp.getString("keyVibration", "false").equals("true") ) { vibrator.vibrate(50); }

        String bText = (String) bigEditText.getText().toString();
        String sText = (String) smallEditText.getText().toString();

        int bLength = bText.length();
        int sLength = sText.length();

        switch ( view.getId() ) {

            case R.id.memoryClearBtn:
                break;

            case R.id.memoryAddBtn:
                break;

            case R.id.memorySubtractBtn:
                break;

            case R.id.memoryRecallBtn:
                break;
        }
    }
}
