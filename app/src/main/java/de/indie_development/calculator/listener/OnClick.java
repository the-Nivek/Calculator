package de.indie_development.calculator.listener;

import android.view.View;
import android.view.View.OnClickListener;

import javax.script.ScriptException;

import de.indie_development.calculator.R;

import static de.indie_development.calculator.activities.MainActivity.vibrator;
import static de.indie_development.calculator.util.Values.bigEditText;
import static de.indie_development.calculator.util.Values.divideError;
import static de.indie_development.calculator.util.Values.engine;
import static de.indie_development.calculator.util.Values.smallEditText;
import static de.indie_development.calculator.util.Values.sp;

public class OnClick implements OnClickListener {

    @Override
    public void onClick(View view) {

        if ( sp.getString("keyVibration", "false").equals("true") ) { vibrator.vibrate(50); }

        String bText = (String) bigEditText.getText().toString();
        String sText = (String) smallEditText.getText().toString();

        int bLength = bText.length();
        int sLength = sText.length();

        switch ( view.getId() ) {

            case R.id.equalsBtn:

                if ( bLength > 0 && sLength > 0 ) {

                    if ( !bText.equals(divideError) ) {

                        if ( bText.startsWith(",") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText + "0" + bText + " =");
                            bigEditText.setText("");

                        } else if ( bText.endsWith(",") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText + "" + bText.subSequence(0, bLength-1) + " =");
                            bigEditText.setText("");

                        } else {

                            if ( bText.contains(",") ) {

                                if ( bText.subSequence(bText.indexOf(",") + 1, bLength).equals("0") ) {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "" + bText.subSequence(0, bText.indexOf(",")) + " =");
                                    bigEditText.setText("");

                                } else {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "" + bText + " =");
                                    bigEditText.setText("");
                                }

                            } else {

                                smallEditText.setText(null);
                                smallEditText.append(sText + "" + bText + " =");
                                bigEditText.setText("");
                            }
                        }
                        if ( !smallEditText.getText().toString().contains("÷ 0") ) {

                            String term = smallEditText.getText().subSequence(0, smallEditText.getText().length() - 2).toString();
                            term = term.replaceAll(",", ".");
                            term = term.replaceAll("×", "*");
                            term = term.replaceAll("÷", "/");
                            term = term.replaceAll("mod", "%");

                            double res = 0;

                            try {
                                res = (double) engine.eval(term);

                            } catch (ScriptException e) {
                                e.printStackTrace();
                            }

                            String result = String.valueOf(res);
                            result = result.replace(".", ",");

                            if ( result.contains(",") ) {

                                if ( result.subSequence(result.indexOf(",") + 1, result.length()).equals("0") ) {

                                    result = (String) result.subSequence(0, result.indexOf(","));
                                }
                            }

                            if ( result.contains("E") ) {

                                result = result.replace("E", "e");
                            }

                            bigEditText.setText(null);
                            bigEditText.append(result);

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(divideError);
                        }
                    }
                }
                break;


            case R.id.deleteBtn:

                if ( bLength > 0 ) {

                    if ( !bText.equals(divideError) ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1));

                        } else {

                            if ( bLength == 4 ) {

                                bigEditText.setText(null);

                            } else {

                                if ( !bText.equals("(-0,)") ) {

                                    bigEditText.setText(null);
                                    bigEditText.append(bText.subSequence(0, bLength - 2) + ")");

                                } else {

                                    bigEditText.setText(null);
                                }
                            }
                        }

                    } else {

                        bigEditText.setText("");
                        smallEditText.setText("");
                    }
                }
                break;

            case R.id.clearBtn:

                bigEditText.setText("");
                smallEditText.setText("");
                break;

            case R.id.moreBtn:


                break;
        }
    }
}
