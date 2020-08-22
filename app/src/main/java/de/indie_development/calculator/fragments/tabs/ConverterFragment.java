package de.indie_development.calculator.fragments.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import de.indie_development.calculator.R;

import static de.indie_development.calculator.util.Values.sp;

public class ConverterFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter, container, false);

        switch ( sp.getString("fontSize", "default") ) {

            case "small": view = inflater.inflate(R.layout.fragment_converter_small, container, false); break;

            case "default": view = inflater.inflate(R.layout.fragment_converter, container, false); break;

            case "large": view = inflater.inflate(R.layout.fragment_converter_large, container, false); break;

            case "extra large": view = inflater.inflate(R.layout.fragment_converter_extra_large, container, false); break;
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_converter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_history:

                final Snackbar history = Snackbar.make(getView(), "Opens History Activity", Snackbar.LENGTH_INDEFINITE);
                history.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        history.dismiss();
                    }
                }).show();

                return true;

            case R.id.action_physical_quantity:

                final Snackbar physicalQuantity = Snackbar.make(getView(), "Sets the physical quantity", Snackbar.LENGTH_INDEFINITE);
                physicalQuantity.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        physicalQuantity.dismiss();
                    }
                }).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}