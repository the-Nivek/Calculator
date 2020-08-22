package de.indie_development.calculator.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import de.indie_development.calculator.R;
import de.indie_development.calculator.activities.settings.AboutActivity;
import de.indie_development.calculator.activities.settings.GeneralActivity;
import de.indie_development.calculator.activities.settings.HelpActivity;
import de.indie_development.calculator.activities.settings.LayoutActivity;

import static de.indie_development.calculator.util.Methods.setTitleStyle;
import static de.indie_development.calculator.util.Values.sp;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // SET CONTENT VIEW
        switch ( sp.getString("fontSize", "default") ) {

            case "small": setContentView(R.layout.activity_settings_small); break;

            case "default": setContentView(R.layout.activity_settings); break;

            case "large": setContentView(R.layout.activity_settings_large); break;

            case "extra large": setContentView(R.layout.activity_settings_extra_large); break;
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


        setTitle(R.string.settings);


        // ACTION BAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.i_back);


        // INITIALIZE OBJECTS
        RelativeLayout settings_general = findViewById(R.id.settings_general),
                       settings_layout = findViewById(R.id.settings_layout),
                       settings_help = findViewById(R.id.settings_help),
                       settings_about = findViewById(R.id.settings_about);

        TextView general = findViewById(R.id.general),
                 generalSummary = findViewById(R.id.generalSummary),
                 layout = findViewById(R.id.layout),
                 layoutSummary = findViewById(R.id.layoutSummary),
                 help = findViewById(R.id.help),
                 helpSummary = findViewById(R.id.helpSummary),
                 about = findViewById(R.id.about),
                 aboutSummary = findViewById(R.id.aboutSummary);

        TextView[] allTV = {general, generalSummary, layout, layoutSummary, help, helpSummary, about, aboutSummary};


        // ON CLICK
        settings_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GeneralActivity.class));
            }
        });

        settings_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LayoutActivity.class));
            }
        });

        settings_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HelpActivity.class));
            }
        });

        settings_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            }
        });


        // GET FONT
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
        for ( TextView tv : allTV ) { tv.setTypeface(typeface, style); }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}