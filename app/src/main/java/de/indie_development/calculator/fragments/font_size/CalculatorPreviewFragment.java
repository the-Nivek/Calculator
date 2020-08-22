package de.indie_development.calculator.fragments.font_size;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import de.indie_development.calculator.R;

import static de.indie_development.calculator.util.Values.sp;

public class CalculatorPreviewFragment extends Fragment {

    public static View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator_preview, container, false);
        v = view;

        EditText bet = view.findViewById(R.id.bigEditText);
        EditText set = view.findViewById(R.id.smallEditText);
        TextView mtv = view.findViewById(R.id.memoryTextView);

        Button mcb = view.findViewById(R.id.memoryClearBtn);
        Button mab = view.findViewById(R.id.memoryAddBtn);
        Button msb = view.findViewById(R.id.memorySubtractBtn);
        Button mrb = view.findViewById(R.id.memoryRecallBtn);
        Button cb = view.findViewById(R.id.clearBtn);
        Button mb = view.findViewById(R.id.moduloBtn);
        Button divB = view.findViewById(R.id.divideBtn);
        Button pb = view.findViewById(R.id.percentBtn);
        Button obb = view.findViewById(R.id.openBracketBtn);
        Button cbb = view.findViewById(R.id.closedBracketBtn);
        Button mub = view.findViewById(R.id.multiplyBtn);

        TextView p = view.findViewById(R.id.preview);

        bet.setText(null);
        bet.append(getString(R.string.betPreview));
        set.setText(null);
        set.append(getString(R.string.setPreview));


        // SET FONT
        Typeface typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);

        switch ( sp.getString("font", "Sans Serif Medium") ) {

            case "Sans Serif Medium":
                typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);
                break;

            case "Calibri":
                typeface = ResourcesCompat.getFont(getContext(), R.font.calibri);
                break;

            case "Lato":
                typeface = ResourcesCompat.getFont(getContext(), R.font.lato);
                break;

            case "Eras Demi ITC":
                typeface = ResourcesCompat.getFont(getContext(), R.font.eras_demi_itc);
                break;

            case "Eras Light ITC":
                typeface = ResourcesCompat.getFont(getContext(), R.font.eras_light_itc);
                break;

            case "Agency FB":
                typeface = ResourcesCompat.getFont(getContext(), R.font.agency_fb);
                break;

            case "Ink Free":
                typeface = ResourcesCompat.getFont(getContext(), R.font.ink_free);
                break;

            case "Segoe Print":
                typeface = ResourcesCompat.getFont(getContext(), R.font.segoe_print);
                break;
        }

        for ( Button b : new Button[]{mcb, mab, msb, mrb, cb, mb, divB, pb, obb, cbb, mub} ) {
            b.setTypeface(typeface);
        }

        bet.setTypeface(typeface);
        set.setTypeface(typeface);
        mtv.setTypeface(typeface);
        p.setTypeface(typeface);


        // SET SIZE
        switch ( sp.getString("fontSize", "default") ) {

            case "small":
                changeFontSizeToSmall();
                changeIconSizeToSmall();
                break;

            case "default":
                changeFontSizeToDefault();
                changeIconSizeToDefault();
                break;

            case "large":
                changeFontSizeToLarge();
                changeIconSizeToLarge();
                break;

            case "extra large":
                changeFontSizeToExtraLarge();
                changeIconSizeToExtraLarge();
                break;
        }


        return view;
    }

    // SMALL
    public static void changeFontSizeToSmall() {

        EditText bet = getV().findViewById(R.id.bigEditText);
        EditText set = getV().findViewById(R.id.smallEditText);
        TextView mtv = getV().findViewById(R.id.memoryTextView);

        Button mcb = getV().findViewById(R.id.memoryClearBtn);
        Button mab = getV().findViewById(R.id.memoryAddBtn);
        Button msb = getV().findViewById(R.id.memorySubtractBtn);
        Button mrb = getV().findViewById(R.id.memoryRecallBtn);
        Button cb = getV().findViewById(R.id.clearBtn);
        Button mb = getV().findViewById(R.id.moduloBtn);
        Button divB = getV().findViewById(R.id.divideBtn);
        Button pb = getV().findViewById(R.id.percentBtn);
        Button obb = getV().findViewById(R.id.openBracketBtn);
        Button cbb = getV().findViewById(R.id.closedBracketBtn);
        Button mub = getV().findViewById(R.id.multiplyBtn);

        bet.setTextSize(27);
        set.setTextSize(17);
        mtv.setTextSize(13);

        for ( Button btn : new Button[]{mcb, mab, msb, mrb} ) {
            btn.setTextSize(17);
        }

        for ( Button btn : new Button[]{cb, mb, divB, pb, obb, cbb, mub} ) {
            btn.setTextSize(22);
        }
    }

    public static void changeIconSizeToSmall() {

        ImageButton db = getV().findViewById(R.id.deleteBtn);
        db.setImageResource(R.drawable.i_delete_small);
    }


    // DEFAULT
    public static void changeFontSizeToDefault() {

        EditText bet = getV().findViewById(R.id.bigEditText);
        EditText set = getV().findViewById(R.id.smallEditText);
        TextView mtv = getV().findViewById(R.id.memoryTextView);

        Button mcb = getV().findViewById(R.id.memoryClearBtn);
        Button mab = getV().findViewById(R.id.memoryAddBtn);
        Button msb = getV().findViewById(R.id.memorySubtractBtn);
        Button mrb = getV().findViewById(R.id.memoryRecallBtn);
        Button cb = getV().findViewById(R.id.clearBtn);
        Button mb = getV().findViewById(R.id.moduloBtn);
        Button divB = getV().findViewById(R.id.divideBtn);
        Button pb = getV().findViewById(R.id.percentBtn);
        Button obb = getV().findViewById(R.id.openBracketBtn);
        Button cbb = getV().findViewById(R.id.closedBracketBtn);
        Button mub = getV().findViewById(R.id.multiplyBtn);

        bet.setTextSize(30);
        set.setTextSize(20);
        mtv.setTextSize(15);

        for ( Button btn : new Button[]{mcb, mab, msb, mrb} ) {
            btn.setTextSize(20);
        }

        for ( Button btn : new Button[]{cb, mb, divB, pb, obb, cbb, mub} ) {
            btn.setTextSize(25);
        }
    }

    public static void changeIconSizeToDefault() {

        ImageButton db = getV().findViewById(R.id.deleteBtn);
        db.setImageResource(R.drawable.i_delete);
    }


    // LARGE
    public static void changeFontSizeToLarge() {

        EditText bet = getV().findViewById(R.id.bigEditText);
        EditText set = getV().findViewById(R.id.smallEditText);
        TextView mtv = getV().findViewById(R.id.memoryTextView);

        Button mcb = getV().findViewById(R.id.memoryClearBtn);
        Button mab = getV().findViewById(R.id.memoryAddBtn);
        Button msb = getV().findViewById(R.id.memorySubtractBtn);
        Button mrb = getV().findViewById(R.id.memoryRecallBtn);
        Button cb = getV().findViewById(R.id.clearBtn);
        Button mb = getV().findViewById(R.id.moduloBtn);
        Button divB = getV().findViewById(R.id.divideBtn);
        Button pb = getV().findViewById(R.id.percentBtn);
        Button obb = getV().findViewById(R.id.openBracketBtn);
        Button cbb = getV().findViewById(R.id.closedBracketBtn);
        Button mub = getV().findViewById(R.id.multiplyBtn);

        bet.setTextSize(33);
        set.setTextSize(23);
        mtv.setTextSize(17);

        for ( Button btn : new Button[]{mcb, mab, msb, mrb} ) {
            btn.setTextSize(23);
        }

        for ( Button btn : new Button[]{cb, mb, divB, pb, obb, cbb, mub} ) {
            btn.setTextSize(28);
        }
    }

    public static void changeIconSizeToLarge() {

        ImageButton db = getV().findViewById(R.id.deleteBtn);
        db.setImageResource(R.drawable.i_delete_large);
    }


    // EXTRA LARGE
    public static void changeFontSizeToExtraLarge() {

        EditText bet = getV().findViewById(R.id.bigEditText);
        EditText set = getV().findViewById(R.id.smallEditText);
        TextView mtv = getV().findViewById(R.id.memoryTextView);

        Button mcb = getV().findViewById(R.id.memoryClearBtn);
        Button mab = getV().findViewById(R.id.memoryAddBtn);
        Button msb = getV().findViewById(R.id.memorySubtractBtn);
        Button mrb = getV().findViewById(R.id.memoryRecallBtn);
        Button cb = getV().findViewById(R.id.clearBtn);
        Button mb = getV().findViewById(R.id.moduloBtn);
        Button divB = getV().findViewById(R.id.divideBtn);
        Button pb = getV().findViewById(R.id.percentBtn);
        Button obb = getV().findViewById(R.id.openBracketBtn);
        Button cbb = getV().findViewById(R.id.closedBracketBtn);
        Button mub = getV().findViewById(R.id.multiplyBtn);

        bet.setTextSize(36);
        set.setTextSize(26);
        mtv.setTextSize(19);

        for ( Button btn : new Button[]{mcb, mab, msb, mrb} ) {
            btn.setTextSize(26);
        }

        for ( Button btn : new Button[]{cb, mb, divB, pb, obb, cbb, mub} ) {
            btn.setTextSize(31);
        }
    }

    public static void changeIconSizeToExtraLarge() {

        ImageButton db = getV().findViewById(R.id.deleteBtn);
        db.setImageResource(R.drawable.i_delete_extra_large);
    }


    // GET VIEW
    public static View getV() { return v; }
}