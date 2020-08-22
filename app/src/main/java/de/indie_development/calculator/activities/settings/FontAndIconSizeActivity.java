package de.indie_development.calculator.activities.settings;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import de.indie_development.calculator.R;
import de.indie_development.calculator.util.FontSizePreviewPagerAdapter;

import static de.indie_development.calculator.fragments.font_size.CalculatorPreviewFragment.changeFontSizeToDefault;
import static de.indie_development.calculator.fragments.font_size.CalculatorPreviewFragment.changeFontSizeToExtraLarge;
import static de.indie_development.calculator.fragments.font_size.CalculatorPreviewFragment.changeFontSizeToLarge;
import static de.indie_development.calculator.fragments.font_size.CalculatorPreviewFragment.changeFontSizeToSmall;
import static de.indie_development.calculator.fragments.font_size.CalculatorPreviewFragment.changeIconSizeToDefault;
import static de.indie_development.calculator.fragments.font_size.CalculatorPreviewFragment.changeIconSizeToExtraLarge;
import static de.indie_development.calculator.fragments.font_size.CalculatorPreviewFragment.changeIconSizeToLarge;
import static de.indie_development.calculator.fragments.font_size.CalculatorPreviewFragment.changeIconSizeToSmall;
import static de.indie_development.calculator.util.Methods.setTitleStyle;
import static de.indie_development.calculator.util.Values.editor;
import static de.indie_development.calculator.util.Values.sp;

public class FontAndIconSizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // SET CONTENT VIEW
        switch ( sp.getString("fontSize", "default") ) {

            case "small": setContentView(R.layout.activity_font_and_icon_size_small); break;

            case "default": setContentView(R.layout.activity_font_and_icon_size); break;

            case "large": setContentView(R.layout.activity_font_and_icon_size_large); break;

            case "extra large": setContentView(R.layout.activity_font_and_icon_size_extra_large); break;
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


        setTitle(R.string.settings_fontAndIconSize);


        // INITIALIZE OBJECTS
        FontSizePreviewPagerAdapter pagerAdapter = new FontSizePreviewPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);

        SpringDotsIndicator sdi = findViewById(R.id.spring_dots_indicator);
        sdi.setViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.i_back);


        // INITIALIZE OBJECTS
        SeekBar seekBar_fs = findViewById(R.id.seekBar_fs),
                seekBar_is = findViewById(R.id.seekBar_is);

        final TextView summary_fs = findViewById(R.id.summary_fs),
                       summary_is = findViewById(R.id.summary_is);

        TextView title_fs = findViewById(R.id.title_fs),
                 title_is = findViewById(R.id.title_is);

        TextView[] allTV = {title_fs, summary_fs, title_is, summary_is};


        // PASTE CONTENT
        seekBar_fs.setProgress(sp.getInt("fontSizeValue", 1));
        seekBar_is.setProgress(sp.getInt("iconSizeValue", 1));

        if ( sp.getString("fontSize", "default").equals("small") ) { summary_fs.setText(R.string.size_small); }
        else if ( sp.getString("fontSize", "default").equals("default") ) { summary_fs.setText(R.string.size_default); }
        else if ( sp.getString("fontSize", "default").equals("large") ) { summary_fs.setText(R.string.size_large); }
        else { summary_fs.setText(R.string.size_extra_large); }

        if ( sp.getString("iconSize", "default").equals("small") ) { summary_is.setText(R.string.size_small); }
        else if ( sp.getString("iconSize", "default").equals("default") ) { summary_is.setText(R.string.size_default); }
        else if ( sp.getString("iconSize", "default").equals("large") ) { summary_is.setText(R.string.size_large); }
        else { summary_is.setText(R.string.size_extra_large); }


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


        // SET TITLE STYLE
        setTitleStyle(toolbar, typeface, style);


        // SET TYPEFACE
        for ( TextView tv : allTV ) { tv.setTypeface(typeface); }


        // ON ACTION
        seekBar_fs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch ( i ) {

                    case 0:
                        editor.putString("fontSize", "small").putInt("fontSizeValue", i).apply();
                        summary_fs.setText(R.string.size_small);
                        changeFontSizeToSmall();
                        break;

                    case 1:
                        editor.putString("fontSize", "default").putInt("fontSizeValue", i).apply();
                        summary_fs.setText(R.string.size_default);
                        changeFontSizeToDefault();
                        break;

                    case 2:
                        editor.putString("fontSize", "large").putInt("fontSizeValue", i).apply();
                        summary_fs.setText(R.string.size_large);
                        changeFontSizeToLarge();
                        break;

                    case 3:
                        editor.putString("fontSize", "extra large").putInt("fontSizeValue", i).apply();
                        summary_fs.setText(R.string.size_extra_large);
                        changeFontSizeToExtraLarge();
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_is.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch ( i ) {

                    case 0:
                        editor.putString("iconSize", "small").putInt("iconSizeValue", i).apply();
                        summary_is.setText(getString(R.string.size_small));
                        changeIconSizeToSmall();
                        break;

                    case 1:
                        editor.putString("iconSize", "default").putInt("iconSizeValue", i).apply();
                        summary_is.setText(getString(R.string.size_default));
                        changeIconSizeToDefault();
                        break;

                    case 2:
                        editor.putString("iconSize", "large").putInt("iconSizeValue", i).apply();
                        summary_is.setText(getString(R.string.size_large));
                        changeIconSizeToLarge();
                        break;

                    case 3:
                        editor.putString("iconSize", "extra large").putInt("iconSizeValue", i).apply();
                        summary_is.setText(getString(R.string.size_extra_large));
                        changeIconSizeToExtraLarge();
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
                startActivity(new Intent(this, LayoutActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

// TODO: RESTART APP:
//Intent intent = new Intent(this, MainActivity.class);
//intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//startActivity(intent);

// TODO: RESTART APP:
//Intent intent = new Intent(LayoutActivity.this, MainActivity.class);
//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
//startActivity(intent);