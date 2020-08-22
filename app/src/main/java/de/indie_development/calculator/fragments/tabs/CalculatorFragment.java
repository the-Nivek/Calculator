package de.indie_development.calculator.fragments.tabs;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import de.indie_development.calculator.R;
import de.indie_development.calculator.listener.OnClick;
import de.indie_development.calculator.listener.OnLongClick;
import de.indie_development.calculator.listener.buttons.DisplayButtons;
import de.indie_development.calculator.listener.buttons.MemoryButtons;
import de.indie_development.calculator.listener.buttons.OperatorButtons;

import static de.indie_development.calculator.util.Values.addBtn;
import static de.indie_development.calculator.util.Values.bg_view;
import static de.indie_development.calculator.util.Values.bigEditText;
import static de.indie_development.calculator.util.Values.clearBtn;
import static de.indie_development.calculator.util.Values.commaBtn;
import static de.indie_development.calculator.util.Values.deleteBtn;
import static de.indie_development.calculator.util.Values.divideBtn;
import static de.indie_development.calculator.util.Values.editor;
import static de.indie_development.calculator.util.Values.eightBtn;
import static de.indie_development.calculator.util.Values.equalsBtn;
import static de.indie_development.calculator.util.Values.fiveBtn;
import static de.indie_development.calculator.util.Values.fourBtn;
import static de.indie_development.calculator.util.Values.line;
import static de.indie_development.calculator.util.Values.memoryAddBtn;
import static de.indie_development.calculator.util.Values.memoryClearBtn;
import static de.indie_development.calculator.util.Values.memoryRecallBtn;
import static de.indie_development.calculator.util.Values.memorySubtractBtn;
import static de.indie_development.calculator.util.Values.memoryTextView;
import static de.indie_development.calculator.util.Values.moduloBtn;
import static de.indie_development.calculator.util.Values.moreBtn;
import static de.indie_development.calculator.util.Values.multiplyBtn;
import static de.indie_development.calculator.util.Values.nineBtn;
import static de.indie_development.calculator.util.Values.oneBtn;
import static de.indie_development.calculator.util.Values.percentBtn;
import static de.indie_development.calculator.util.Values.powerBtn;
import static de.indie_development.calculator.util.Values.rootBtn;
import static de.indie_development.calculator.util.Values.sevenBtn;
import static de.indie_development.calculator.util.Values.signBtn;
import static de.indie_development.calculator.util.Values.sixBtn;
import static de.indie_development.calculator.util.Values.smallEditText;
import static de.indie_development.calculator.util.Values.sp;
import static de.indie_development.calculator.util.Values.subtractBtn;
import static de.indie_development.calculator.util.Values.threeBtn;
import static de.indie_development.calculator.util.Values.twoBtn;
import static de.indie_development.calculator.util.Values.zeroBtn;

public class CalculatorFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);


        // SET VIEW
        switch ( sp.getString("fontSize", "default") ) {

            case "small": view = inflater.inflate(R.layout.fragment_calculator_small, container, false); break;

            case "default": view = inflater.inflate(R.layout.fragment_calculator, container, false); break;

            case "large": view = inflater.inflate(R.layout.fragment_calculator_large, container, false); break;

            case "extra large": view = inflater.inflate(R.layout.fragment_calculator_extra_large, container, false); break;
        }


        // INITIALIZE OBJECTS
        bg_view = view.findViewById(R.id.bg_view);
        line = view.findViewById(R.id.line);

        bigEditText = view.findViewById(R.id.bigEditText);
        smallEditText = view.findViewById(R.id.smallEditText);
        memoryTextView = view.findViewById(R.id.memoryTextView);

        memoryClearBtn = view.findViewById(R.id.memoryClearBtn);
        memoryAddBtn = view.findViewById(R.id.memoryAddBtn);
        memorySubtractBtn = view.findViewById(R.id.memorySubtractBtn);
        memoryRecallBtn = view.findViewById(R.id.memoryRecallBtn);

        deleteBtn = (ImageButton) view.findViewById(R.id.deleteBtn);
        clearBtn = view.findViewById(R.id.clearBtn);
        equalsBtn = view.findViewById(R.id.equalsBtn);
        moreBtn = view.findViewById(R.id.moreBtn);

        addBtn = view.findViewById(R.id.addBtn);
        subtractBtn = view.findViewById(R.id.subtractBtn);
        multiplyBtn = view.findViewById(R.id.multiplyBtn);
        divideBtn = view.findViewById(R.id.divideBtn);
        moduloBtn = view.findViewById(R.id.moduloBtn);
        powerBtn = view.findViewById(R.id.powerBtn);
        rootBtn = view.findViewById(R.id.rootBtn);
        percentBtn = view.findViewById(R.id.percentBtn);

        zeroBtn = view.findViewById(R.id.zeroBtn);
        oneBtn = view.findViewById(R.id.oneBtn);
        twoBtn = view.findViewById(R.id.twoBtn);
        threeBtn = view.findViewById(R.id.threeBtn);
        fourBtn = view.findViewById(R.id.fourBtn);
        fiveBtn = view.findViewById(R.id.fiveBtn);
        sixBtn = view.findViewById(R.id.sixBtn);
        sevenBtn = view.findViewById(R.id.sevenBtn);
        eightBtn = view.findViewById(R.id.eightBtn);
        nineBtn = view.findViewById(R.id.nineBtn);
        commaBtn = view.findViewById(R.id.commaBtn);
        signBtn = view.findViewById(R.id.signBtn);

        Button[] memoryButtons = {memoryClearBtn, memoryAddBtn, memorySubtractBtn, memoryRecallBtn};
        Button[] buttons = {clearBtn, equalsBtn};
        Button[] operatorButtons = {addBtn, subtractBtn, multiplyBtn, divideBtn, moduloBtn, powerBtn, rootBtn, percentBtn};
        Button[] displayButtons = {zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, commaBtn, signBtn};
        Button[] allButtons = {memoryClearBtn, memoryAddBtn, memorySubtractBtn, memoryRecallBtn, clearBtn, equalsBtn, addBtn, subtractBtn, multiplyBtn, divideBtn, moduloBtn, powerBtn, rootBtn, percentBtn, zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, commaBtn, signBtn};


        // SET ON CLICK LISTENER
        for ( Button btn : memoryButtons ) { btn.setOnClickListener(new MemoryButtons()); }
        for ( Button btn : buttons ) { btn.setOnClickListener(new OnClick()); }
        for ( Button btn : operatorButtons ) { btn.setOnClickListener(new OperatorButtons()); }
        for ( Button btn : displayButtons ) { btn.setOnClickListener(new DisplayButtons()); }

        deleteBtn.setOnClickListener(new OnClick());
        deleteBtn.setOnLongClickListener(new OnLongClick());
        moreBtn.setOnClickListener(new OnClick());


        // PASTE CONTENT
        bigEditText.setText(null);
        bigEditText.append(sp.getString("bigEditText", ""));

        smallEditText.setText(null);
        smallEditText.append(sp.getString("smallEditText", ""));

        memoryTextView.setText(null);
        memoryTextView.append(sp.getString("memoryTextView", "Memory: "));


        // GET FONT
        Typeface typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);;
        switch ( sp.getString("font", "Sans Serif Medium ") ) {

            case "Sans Serif Medium ": break;

            case "Calibri ":
                typeface = ResourcesCompat.getFont(getContext(), R.font.calibri); break;

            case "Lato ":
                typeface = ResourcesCompat.getFont(getContext(), R.font.lato); break;

            case "Eras Demi ITC ":
                typeface = ResourcesCompat.getFont(getContext(), R.font.eras_demi_itc); break;

            case "Eras Light ITC ":
                typeface = ResourcesCompat.getFont(getContext(), R.font.eras_light_itc); break;

            case "Agency FB ":
                typeface = ResourcesCompat.getFont(getContext(), R.font.agency_fb); break;

            case "Ink Free ":
                typeface = ResourcesCompat.getFont(getContext(), R.font.ink_free); break;

            case "Segoe Print ":
                typeface = ResourcesCompat.getFont(getContext(), R.font.segoe_print); break;
        }


        // GET TEXT STYLE
        int style = Typeface.NORMAL;
        switch ( sp.getString("textStyle", "normal") ) {

            case "normal": style = Typeface.NORMAL; break;

            case "italic": style = Typeface.ITALIC; break;

            case "bold": style = Typeface.BOLD; break;
        }


        // SET TYPEFACE
        bigEditText.setTypeface(typeface, style);
        smallEditText.setTypeface(typeface, style);
        memoryTextView.setTypeface(typeface, style);

        for ( Button btn : allButtons ) { btn.setTypeface(typeface, style); }


        // SET DRAWABLE SIZE
        switch ( sp.getString("iconSize", "default") ) {

            case "small":
                deleteBtn.setImageResource(R.drawable.i_delete_small);
                moreBtn.setImageResource(R.drawable.i_more_small);
                break;

            case "default":
                deleteBtn.setImageResource(R.drawable.i_delete);
                moreBtn.setImageResource(R.drawable.i_more);
                break;

            case "large":
                deleteBtn.setImageResource(R.drawable.i_delete_large);
                moreBtn.setImageResource(R.drawable.i_more_large);
                break;

            case "extra large":
                deleteBtn.setImageResource(R.drawable.i_delete_extra_large);
                moreBtn.setImageResource(R.drawable.i_more_extra_large);
                break;
        }

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        // SAVE CONTENT
        editor.putString("bigEditText", bigEditText.getText().toString())
              .putString("smallEditText", smallEditText.getText().toString())
              .putString("memoryTextView", memoryTextView.getText().toString())
              .apply();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calculator, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_history:

                // OPEN PLACEHOLDER
                final Snackbar history = Snackbar.make(getView(), "Opens History Activity", Snackbar.LENGTH_INDEFINITE);
                history.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        history.dismiss();
                    }
                }).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}