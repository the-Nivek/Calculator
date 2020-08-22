package de.indie_development.calculator.fragments.font_size;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import de.indie_development.calculator.R;

import static de.indie_development.calculator.util.Values.sp;

public class ConverterPreviewFragment extends Fragment {

    public static View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter_preview, container, false);
        v = view;

        TextView c_availableSoon = view.findViewById(R.id.c_availableSoon);


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

        c_availableSoon.setTypeface(typeface);


        return view;
    }

    public static View getV() { return v; }
}