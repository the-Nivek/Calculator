package de.indie_development.calculator.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;
import java.util.Locale;

import de.indie_development.calculator.R;

import static de.indie_development.calculator.util.Values.editor;
import static de.indie_development.calculator.util.Values.sp;

public class Methods {

    public static void setTitleStyle(Toolbar toolbar, Typeface typeface, int style) {

        for ( int i = 0; i < toolbar.getChildCount(); i++ ) {

            View view = toolbar.getChildAt(i);

            if ( view instanceof TextView ) {
                TextView textView = (TextView) view;
                textView.setTypeface(typeface, style);
            }
        }
    }

    public static void setLanguage(Resources res, Object value) {

        String language = Locale.getDefault().getLanguage();

        if ( !value.equals("auto") ) { language = value.toString(); }

        Locale locale = new Locale(language);
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = new Configuration();
        config.locale = locale;
        res.updateConfiguration(config, dm);
    }

    public static void onShowListDialog(final Context context, String title, final String[] listItems, final String itemSelectedPref, final String pref, final TextView summary) {

        AlertDialog.Builder adb = new AlertDialog.Builder(context, R.style.WhiteAlertDialogTheme);
        switch ( sp.getString("theme", "white") ) {

            case "white":
                adb = new AlertDialog.Builder(context, R.style.WhiteAlertDialogTheme);
                break;

            case "gray":
                adb = new AlertDialog.Builder(context, R.style.GrayAlertDialogTheme);
                break;

            case "black":
                adb = new AlertDialog.Builder(context, R.style.BlackAlertDialogTheme);
                break;
        }

        adb.setTitle(title)
                .setSingleChoiceItems(listItems, sp.getInt(itemSelectedPref, 0), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        editor.putInt(itemSelectedPref, i)
                              .putString(pref, listItems[i]).apply();

                        summary.setText(listItems[i]);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog ad = adb.create();
        ad.show();
    }


    //  CALCULATOR
    public static String calculateWithPercents(String percents, int length, double number) {

        for ( int i = 0; i < length; i++ ) {

            number /= 100;
        }

        String when = String.valueOf(number);
        when = setDigits(when).replace(".", ",");

        return when;
    }

    public static String setDigits(String number) {

        boolean justTenDigits = false;

        for ( int i = 0; i < 7; i++ ) {

            if ( number.endsWith("-" + String.valueOf(i+1)) ) {

                justTenDigits = true;
            }
        }

        if ( justTenDigits ) {

            DecimalFormat df = new DecimalFormat("#,###.##########");
            number = df.format(Double.parseDouble(number));
        }

        return number;
    }

    public static boolean onlyZeros(String text) {

        boolean onlyZeros = false;

        for ( int i = 0; i < text.length(); i++ ) {

            if ( text.charAt(i) == '0' ) {

                onlyZeros = true;

            } else {

                onlyZeros = false;
                break;
            }
        }

        return onlyZeros;
    }
}
