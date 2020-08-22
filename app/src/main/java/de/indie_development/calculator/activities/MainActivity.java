package de.indie_development.calculator.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import de.indie_development.calculator.R;
import de.indie_development.calculator.util.PagerAdapter;

import static de.indie_development.calculator.util.Methods.setLanguage;
import static de.indie_development.calculator.util.Methods.setTitleStyle;
import static de.indie_development.calculator.util.Values.editor;
import static de.indie_development.calculator.util.Values.sp;

public class MainActivity extends AppCompatActivity {

    private static Resources res;
    private static Context cont;
    public static Vibrator vibrator;

    private Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        res = getResources();
        cont = this;
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);


        // SET CONTENT VIEW
        switch ( sp.getString("fontSize", "default") ) {

            case "small": setContentView(R.layout.activity_main_small); break;

            case "default": setContentView(R.layout.activity_main); break;

            case "large": setContentView(R.layout.activity_main_large); break;

            case "extra large": setContentView(R.layout.activity_main_extra_large); break;
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


        setLanguage(res, sp.getString("language", "auto"));
        setTitle(R.string.appName);


        // INITIALIZE OBJECTS
        PagerAdapter pagerAdapter = new PagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        // GET FONT
        Typeface typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);
        switch ( sp.getString("font", "Sans Serif Medium ") ) {

            case "Sans Serif Medium ": break;

            case "Calibri ":
                typeface = ResourcesCompat.getFont(this, R.font.calibri); break;

            case "Lato ":
                typeface = ResourcesCompat.getFont(this, R.font.lato); break;

            case "Eras Demi ITC ":
                typeface = ResourcesCompat.getFont(this, R.font.eras_demi_itc); break;

            case "Eras Light ITC ":
                typeface = ResourcesCompat.getFont(this, R.font.eras_light_itc); break;

            case "Agency FB ":
                typeface = ResourcesCompat.getFont(this, R.font.agency_fb); break;

            case "Ink Free ":
                typeface = ResourcesCompat.getFont(this, R.font.ink_free); break;

            case "Segoe Print ":
                typeface = ResourcesCompat.getFont(this, R.font.segoe_print); break;
        }


        // GET TEXT STYLE
        int style = Typeface.NORMAL;
        switch ( sp.getString("textStyle", "normal") ) {

            case "normal": style = Typeface.NORMAL; break;

            case "italic": style = Typeface.ITALIC; break;

            case "bold": style = Typeface.BOLD; break;
        }


        // GET TAB TEXT SIZE
        int textSize = 0;
        switch ( sp.getString("fontSize", "default") ) {

            case "small": textSize = 13; break;

            case "default": textSize = 15; break;

            case "large": textSize = 17; break;

            case "extra large": textSize = 19; break;
        }


        // SET TITLE STYLE
        setTitleStyle(toolbar, typeface, style);


        // SET TAB STYLE
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.layout_custom_tab, null);
        tabOne.setText(tabs.getTabAt(0).getText());
        tabOne.setTypeface(typeface, style);
        tabOne.setTextSize(textSize);
        tabs.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.layout_custom_tab, null);
        tabTwo.setText(tabs.getTabAt(1).getText());
        tabOne.setTypeface(typeface, style);
        tabOne.setTextSize(textSize);
        tabs.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.layout_custom_tab, null);
        tabThree.setText(tabs.getTabAt(2).getText());
        tabOne.setTypeface(typeface, style);
        tabOne.setTextSize(textSize);
        tabs.getTabAt(2).setCustomView(tabThree);


        // OPEN NEWS DIALOG
        if ( sp.getString("version", getString(R.string.version)).equals(getString(R.string.version)) ) {

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.layout_update_popup);

            okBtn = dialog.findViewById(R.id.okBtn);
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            editor.putString("version", "1.4-BETA").apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Resources getResourses() { return res; }
    public static Context getCont() { return cont; }
}