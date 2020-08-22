package de.indie_development.calculator.util;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import de.indie_development.calculator.R;

import static de.indie_development.calculator.activities.MainActivity.getCont;

public class Values {

    public static ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

    // open Shared Preferences File
    public static SharedPreferences sp = getCont().getSharedPreferences("saver", 0);
    // initialize editor class
    public static SharedPreferences.Editor editor = sp.edit();


    // CALCULATOR

    // views
    public static View bg_view, line;

    // edit texts and memory text view
    public static EditText bigEditText, smallEditText;
    public static TextView memoryTextView;

    // buttons
    // display buttons
    public static Button zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, commaBtn, signBtn;

    // operator buttons
    public static Button addBtn, subtractBtn, multiplyBtn, divideBtn, moduloBtn, powerBtn, rootBtn, percentBtn;

    // delete, clear, equals and more button
    public static ImageButton deleteBtn, moreBtn;
    public static Button clearBtn, equalsBtn;

    // memory buttons
    public static Button memoryClearBtn, memoryAddBtn, memorySubtractBtn, memoryRecallBtn;

    // errors
    public static String divideError = de.indie_development.calculator.activities.MainActivity.getResourses().getString(R.string.divideError);
}
