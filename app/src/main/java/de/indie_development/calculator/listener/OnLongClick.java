package de.indie_development.calculator.listener;

import android.view.View;
import android.view.View.OnLongClickListener;

import de.indie_development.calculator.R;

import static de.indie_development.calculator.activities.MainActivity.vibrator;
import static de.indie_development.calculator.util.Values.bigEditText;
import static de.indie_development.calculator.util.Values.sp;

public class OnLongClick implements OnLongClickListener {

    @Override
    public boolean onLongClick(View view) {

        if ( sp.getString("keyVibration", "false").equals("true") ) { vibrator.vibrate(100); }

        switch (view.getId()) {

            case R.id.deleteBtn:

                bigEditText.setText("");
        }
        return false;
    }
}
