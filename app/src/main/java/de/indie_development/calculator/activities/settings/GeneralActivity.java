package de.indie_development.calculator.activities.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import de.indie_development.calculator.R;
import de.indie_development.calculator.activities.MainActivity;
import de.indie_development.calculator.activities.SettingsActivity;

import static de.indie_development.calculator.util.Methods.onShowListDialog;
import static de.indie_development.calculator.util.Methods.setTitleStyle;
import static de.indie_development.calculator.util.Values.editor;
import static de.indie_development.calculator.util.Values.sp;

public class GeneralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // SET CONTENT VIEW
        switch ( sp.getString("fontSize", "default") ) {

            case "small": setContentView(R.layout.activity_general_small); break;

            case "default": setContentView(R.layout.activity_general); break;

            case "large": setContentView(R.layout.activity_general_large); break;

            case "extra large": setContentView(R.layout.activity_general_extra_large); break;
        }


        // SET THEME
        switch ( sp.getString("theme", "white") ) {

            case "white":
                setTheme(R.style.WhiteTheme);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // set status bar text color to black
                break;

            case "gray": setTheme(R.style.GrayTheme); break;

            case "black": setTheme(R.style.BlackTheme); break;
        }


        setTitle(R.string.settings_general);


        // ACTION BAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.i_back);


        // INITIALIZE OBJECTS
        RelativeLayout unitOfMeasurement = findViewById(R.id.unitOfMeasurement),
                       numberFormat = findViewById(R.id.numberFormat),
                       language = findViewById(R.id.language),
                       keyVibration = findViewById(R.id.keyVibration);

        TextView title_uom = findViewById(R.id.title_uom),
                 title_nf = findViewById(R.id.title_nf),
                 category_title = findViewById(R.id.category_title),
                 title_l = findViewById(R.id.title_l),
                 title_kv = findViewById(R.id.title_kv);

        final TextView summary_uom = findViewById(R.id.summary_uom),
                       summary_nf = findViewById(R.id.summary_nf),
                       summary_l = findViewById(R.id.summary_l);

        final CheckBox checkbox_kv = findViewById(R.id.checkbox_kv);

        TextView[] allTV = {title_uom, summary_uom, title_nf, summary_nf, title_l, summary_l, title_kv};


        // PASTE CONTENT
        if ( sp.getString("unitOfMeasurement", "radian").equals("radian") ) { summary_uom.setText(getString(R.string.unitOfMeasurement_radian)); }
        else { summary_uom.setText(getString(R.string.unitOfMeasurement_degree)); }

        summary_nf.setText(sp.getString("numberFormat", "1234.5"));

        if ( sp.getString("language", "auto").equals("auto") ) { summary_l.setText(R.string.language_automatically); }
        else if ( sp.getString("language", "auto").equals("en") ) { summary_l.setText(R.string.language_english); }
        else { summary_l.setText(R.string.language_german); }

        checkbox_kv.setChecked(Boolean.parseBoolean(sp.getString("keyVibration", "false")));


        // GET TYPEFACE
        Typeface typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);
        switch ( sp.getString("font", "Sans Serif Medium") ) {

            case "Sans Serif Medium": break;

            case "Calibri":
                typeface = ResourcesCompat.getFont(this, R.font.calibri); break;

            case "Lato":
                typeface = ResourcesCompat.getFont(this, R.font.lato); break;

            case "Eras Demi ITC":
                typeface = ResourcesCompat.getFont(this, R.font.eras_demi_itc); break;

            case "Eras Light ITC":
                typeface = ResourcesCompat.getFont(this, R.font.eras_light_itc); break;

            case "Agency FB":
                typeface = ResourcesCompat.getFont(this, R.font.agency_fb); break;

            case "Ink Free":
                typeface = ResourcesCompat.getFont(this, R.font.ink_free); break;

            case "Segoe Print":
                typeface = ResourcesCompat.getFont(this, R.font.segoe_print); break;
        }


        // GET TEXT STYLE
        int style = Typeface.NORMAL;
        switch ( sp.getString("textStyle", "normal") ) {

            case "normal": style = Typeface.NORMAL; break;

            case "italic": style = Typeface.ITALIC; break;

            case "bold": style = Typeface.BOLD; break;
        }


        // SET TITLE STYLE
        setTitleStyle(toolbar, typeface, style);


        // SET TYPEFACE
        category_title.setTypeface(typeface);
        for ( TextView tv : allTV ) { tv.setTypeface(typeface, style); }


        // ON CLICK
        unitOfMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( sp.getString("unitOfMeasurement", "radian").equals("radian") ) {

                    editor.putString("unitOfMeasurement", "degree").apply();
                    summary_uom.setText(getString(R.string.unitOfMeasurement_degree));

                } else {
                    editor.putString("unitOfMeasurement", "radian").apply();
                    summary_uom.setText(getString(R.string.unitOfMeasurement_radian));
                }
            }
        });

        numberFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShowListDialog(GeneralActivity.this, getString(R.string.title_numberFormat), getResources().getStringArray(R.array.numberFormatValues), "numberFormatItemSelected", "numberFormat", summary_nf);
            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] listItems = getResources().getStringArray(R.array.languageValues);

                AlertDialog.Builder adb = new AlertDialog.Builder(GeneralActivity.this, R.style.WhiteAlertDialogTheme);

                switch ( sp.getString("theme", "white") ) {

                    case "white": adb = new AlertDialog.Builder(GeneralActivity.this, R.style.WhiteAlertDialogTheme); break;

                    case "gray": adb = new AlertDialog.Builder(GeneralActivity.this, R.style.GrayAlertDialogTheme); break;

                    case "black": adb = new AlertDialog.Builder(GeneralActivity.this, R.style.BlackAlertDialogTheme); break;
                }

                adb.setTitle(R.string.title_language)
                        .setSingleChoiceItems(listItems, sp.getInt("languageItemSelected", 0), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String oldValue = sp.getString("language", "auto");

                                if ( listItems[i].equals(getString(R.string.language_automatically)) ) { editor.putString("language", "auto").apply(); }
                                else if ( listItems[i].equals(getString(R.string.language_english)) ) { editor.putString("language", "en").apply(); }
                                else { editor.putString("language", "de").apply(); }

                                editor.putInt("languageItemSelected", i).apply();
                                summary_l.setText(listItems[i]);
                                dialogInterface.dismiss();

                                if ( !sp.getString("language", "auto").equals(oldValue) ) {
                                    Intent intent = new Intent(GeneralActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                }
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog ad = adb.create();
                ad.show();
            }
        });

        keyVibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( checkbox_kv.isChecked() ) {

                    editor.putString("keyVibration", "false").apply();
                    checkbox_kv.setChecked(false);

                } else {
                    editor.putString("keyVibration", "true").apply();
                    checkbox_kv.setChecked(true);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}