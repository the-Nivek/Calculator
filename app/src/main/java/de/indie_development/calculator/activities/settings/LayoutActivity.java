package de.indie_development.calculator.activities.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import de.indie_development.calculator.R;
import de.indie_development.calculator.activities.SettingsActivity;

import static de.indie_development.calculator.util.Methods.setTitleStyle;
import static de.indie_development.calculator.util.Values.editor;
import static de.indie_development.calculator.util.Values.sp;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // SET CONTENT VIEW
        switch ( sp.getString("fontSize", "default") ) {

            case "small": setContentView(R.layout.activity_layout_small); break;

            case "default": setContentView(R.layout.activity_layout); break;

            case "large": setContentView(R.layout.activity_layout_large); break;

            case "extra large": setContentView(R.layout.activity_layout_extra_large); break;
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


        setTitle(R.string.settings_layout);


        // ACTION BAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.i_back);


        // INITIALIZE OBJECTS
        RelativeLayout theme = findViewById(R.id.theme),
                       detailColor = findViewById(R.id.detailColor),
                       textStyle = findViewById(R.id.textStyle),
                       font = findViewById(R.id.font),
                       fontAndIconSize = findViewById(R.id.fontAndIconSize);

        TextView title_t = findViewById(R.id.title_t),
                 title_dc = findViewById(R.id.title_dc),
                 category_title = findViewById(R.id.category_title),
                 title_ts = findViewById(R.id.title_ts),
                 title_f = findViewById(R.id.title_f);

        final TextView summary_t = findViewById(R.id.summary_t),
                       summary_dc = findViewById(R.id.summary_dc),
                       summary_ts = findViewById(R.id.summary_ts),
                       summary_f = findViewById(R.id.summary_f);

        TextView[] allTV = {title_t, summary_t, title_dc, summary_dc, title_ts, summary_ts, title_f, summary_f};


        // PASTE CONTENT
        if ( sp.getString("theme", "white").equals("white") ) { summary_t.setText(getString(R.string.theme_white)); }
        else if ( sp.getString("theme", "white").equals("gray") ) { summary_t.setText(getString(R.string.theme_gray)); }
        else { summary_t.setText(getString(R.string.theme_black)); }

        if ( sp.getString("detailColor", "blue").equals("blue") ) { summary_dc.setText(getString(R.string.detailColor_blue)); }
        else if ( sp.getString("detailColor", "blue").equals("green") ) { summary_dc.setText(getString(R.string.detailColor_green)); }
        else { summary_dc.setText(getString(R.string.detailColor_red)); }

        if ( sp.getString("textStyle", "normal").equals("normal") ) { summary_ts.setText(getString(R.string.style_normal)); }
        else if ( sp.getString("textStyle", "normal").equals("italic") ) { summary_ts.setText(getString(R.string.style_italic)); }
        else { summary_ts.setText(getString(R.string.style_bold)); }

        summary_f.setText(sp.getString("font", "Sans Serif Medium "));


        // GET TYPEFACE
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
        category_title.setTypeface(typeface);
        for ( TextView tv : allTV ) { tv.setTypeface(typeface); }


        // ON CLICK
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] listItems = getResources().getStringArray(R.array.themeValues);

                AlertDialog.Builder adb = new AlertDialog.Builder(LayoutActivity.this, R.style.WhiteAlertDialogTheme);

                switch ( sp.getString("theme", "white") ) {

                    case "white": adb = new AlertDialog.Builder(LayoutActivity.this, R.style.WhiteAlertDialogTheme); break;

                    case "gray": adb = new AlertDialog.Builder(LayoutActivity.this, R.style.GrayAlertDialogTheme); break;

                    case "black": adb = new AlertDialog.Builder(LayoutActivity.this, R.style.BlackAlertDialogTheme); break;
                }

                adb.setTitle(R.string.title_theme)
                        .setSingleChoiceItems(listItems, sp.getInt("themeItemSelected", 0), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String oldValue = sp.getString("theme", "white");

                                if ( listItems[i].equals(getString(R.string.theme_white)) ) { editor.putString("theme", "white").apply(); }
                                else if ( listItems[i].equals(getString(R.string.theme_gray)) ) { editor.putString("theme", "gray").apply(); }
                                else { editor.putString("theme", "black").apply(); }

                                editor.putInt("themeItemSelected", i).apply();
                                summary_t.setText(listItems[i]);
                                dialogInterface.dismiss();

                                if ( !sp.getString("theme", "white").equals(oldValue) ) {
                                    startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
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

        detailColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] listItems = getResources().getStringArray(R.array.detailColorValues);

                AlertDialog.Builder adb = new AlertDialog.Builder(LayoutActivity.this, R.style.WhiteAlertDialogTheme);

                switch ( sp.getString("theme", "white") ) {

                    case "white": adb = new AlertDialog.Builder(LayoutActivity.this, R.style.WhiteAlertDialogTheme); break;

                    case "gray": adb = new AlertDialog.Builder(LayoutActivity.this, R.style.GrayAlertDialogTheme); break;

                    case "black": adb = new AlertDialog.Builder(LayoutActivity.this, R.style.BlackAlertDialogTheme); break;
                }

                adb.setTitle(R.string.title_detailColor)
                        .setSingleChoiceItems(listItems, sp.getInt("detailColorItemSelected", 0), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String oldValue = sp.getString("detailColor", "blue");

                                if ( listItems[i].equals(getString(R.string.detailColor_blue)) ) { editor.putString("detailColor", "blue").apply(); }
                                else if ( listItems[i].equals(getString(R.string.detailColor_green)) ) { editor.putString("detailColor", "green").apply(); }
                                else { editor.putString("detailColor", "red").apply(); }

                                editor.putInt("detailColorItemSelected", i).apply();
                                summary_dc.setText(listItems[i]);
                                dialogInterface.dismiss();

                                if ( !sp.getString("detailColor", "blue").equals(oldValue) ) {
                                    startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
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

        textStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] listItems = getResources().getStringArray(R.array.textStyleValues);

                AlertDialog.Builder adb = new AlertDialog.Builder(LayoutActivity.this, R.style.WhiteAlertDialogTheme);

                switch ( sp.getString("theme", "white") ) {

                    case "white": adb = new AlertDialog.Builder(LayoutActivity.this, R.style.WhiteAlertDialogTheme); break;

                    case "gray": adb = new AlertDialog.Builder(LayoutActivity.this, R.style.GrayAlertDialogTheme); break;

                    case "black": adb = new AlertDialog.Builder(LayoutActivity.this, R.style.BlackAlertDialogTheme); break;
                }

                adb.setTitle(R.string.title_style)
                        .setSingleChoiceItems(listItems, sp.getInt("textStyleItemSelected", 0), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String oldValue = sp.getString("textStyle", "normal");

                                if ( listItems[i].equals(getString(R.string.style_normal)) ) { editor.putString("textStyle", "normal").apply(); }
                                else if ( listItems[i].equals(getString(R.string.style_italic)) ) { editor.putString("textStyle", "italic").apply(); }
                                else { editor.putString("textStyle", "bold").apply(); }

                                editor.putInt("textStyleItemSelected", i).apply();
                                summary_ts.setText(listItems[i]);
                                dialogInterface.dismiss();

                                if ( !sp.getString("textStyle", "normal").equals(oldValue) ) {
                                    startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
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

        font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(LayoutActivity.this);
                dialog.setContentView(R.layout.layout_font_dialog);

                // INITIALIZE OBJECTS
                RadioButton sansSerif = dialog.findViewById(R.id.sansSerif),
                            calibri = dialog.findViewById(R.id.calibri),
                            lato = dialog.findViewById(R.id.lato),
                            erasDemiITC = dialog.findViewById(R.id.erasDemiITC),
                            erasLightITC = dialog.findViewById(R.id.erasLightITC),
                            agencyFB = dialog.findViewById(R.id.agencyFB),
                            inkFree = dialog.findViewById(R.id.inkFree),
                            segoePrint = dialog.findViewById(R.id.segoePrint);

                Button cancelBtn = dialog.findViewById(R.id.cancelBtn);


                // CHECK SELECTED ITEM
                switch ( sp.getString("font", "Sans Serif Medium ") ) {

                    case "Sans Serif Medium ":
                        sansSerif.toggle(); break;

                    case "Calibri ":
                        calibri.toggle(); break;

                    case "Lato ":
                        lato.toggle(); break;

                    case "Eras Demi ITC ":
                        erasDemiITC.toggle(); break;

                    case "Eras Light ITC ":
                        erasLightITC.toggle(); break;

                    case "Agency FB ":
                        agencyFB.toggle(); break;

                    case "Ink Free ":
                        inkFree.toggle(); break;

                    case "Segoe Print ":
                        segoePrint.toggle(); break;
                }


                // ON CLICK
                sansSerif.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldValue = sp.getString("font", "Sans Serif Medium ");

                        editor.putString("font", "Sans Serif Medium ").apply();
                        summary_f.setText("Sans Serif Medium ");
                        dialog.dismiss();

                        if ( !sp.getString("font", "Sans Serif Medium ").equals(oldValue) ) {
                            startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
                        }
                    }
                });

                calibri.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldValue = sp.getString("font", "Sans Serif Medium ");

                        editor.putString("font", "Calibri ").apply();
                        summary_f.setText("Calibri ");
                        dialog.dismiss();

                        if ( !sp.getString("font", "Sans Serif Medium ").equals(oldValue) ) {
                            startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
                        }
                    }
                });

                lato.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldValue = sp.getString("font", "Sans Serif Medium ");

                        editor.putString("font", "Lato ").apply();
                        summary_f.setText("Lato ");
                        dialog.dismiss();

                        if ( !sp.getString("font", "Sans Serif Medium ").equals(oldValue) ) {
                            startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
                        }
                    }
                });

                erasDemiITC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldValue = sp.getString("font", "Sans Serif Medium ");

                        editor.putString("font", "Eras Demi ITC ").apply();
                        summary_f.setText("Eras Demi ITC ");
                        dialog.dismiss();

                        if ( !sp.getString("font", "Sans Serif Medium ").equals(oldValue) ) {
                            startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
                        }
                    }
                });

                erasLightITC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldValue = sp.getString("font", "Sans Serif Medium ");

                        editor.putString("font", "Eras Light ITC ").apply();
                        summary_f.setText("Eras Light ITC ");
                        dialog.dismiss();

                        if ( !sp.getString("font", "Sans Serif Medium ").equals(oldValue) ) {
                            startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
                        }
                    }
                });

                agencyFB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldValue = sp.getString("font", "Sans Serif Medium ");

                        editor.putString("font", "Agency FB ").apply();
                        summary_f.setText("Agency FB ");
                        dialog.dismiss();

                        if ( !sp.getString("font", "Sans Serif Medium ").equals(oldValue) ) {
                            startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
                        }
                    }
                });

                inkFree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldValue = sp.getString("font", "Sans Serif Medium ");

                        editor.putString("font", "Ink Free ").apply();
                        summary_f.setText("Ink Free ");
                        dialog.dismiss();

                        if ( !sp.getString("font", "Sans Serif Medium ").equals(oldValue) ) {
                            startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
                        }
                    }
                });

                segoePrint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldValue = sp.getString("font", "Sans Serif Medium ");

                        editor.putString("font", "Segoe Print ").apply();
                        summary_f.setText("Segoe Print ");
                        dialog.dismiss();

                        if ( !sp.getString("font", "Sans Serif Medium ").equals(oldValue) ) {
                            startActivity(new Intent(LayoutActivity.this, LayoutActivity.class));
                        }
                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        fontAndIconSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FontAndIconSizeActivity.class));
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