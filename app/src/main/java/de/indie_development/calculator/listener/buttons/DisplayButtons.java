package de.indie_development.calculator.listener.buttons;

import android.view.View;
import android.view.View.OnClickListener;

import de.indie_development.calculator.R;

import static de.indie_development.calculator.activities.MainActivity.vibrator;
import static de.indie_development.calculator.util.Methods.onlyZeros;
import static de.indie_development.calculator.util.Values.bigEditText;
import static de.indie_development.calculator.util.Values.divideError;
import static de.indie_development.calculator.util.Values.smallEditText;
import static de.indie_development.calculator.util.Values.sp;

public class DisplayButtons implements OnClickListener {

    @Override
    public void onClick(View view) {

        if ( sp.getString("keyVibration", "false").equals("true") ) { vibrator.vibrate(50); }

        String bText = (String) bigEditText.getText().toString();
        String sText = (String) smallEditText.getText().toString();

        int bLength = bText.length();
        int sLength = sText.length();

        boolean notEquals = !bText.equals(divideError) && !bText.equals("0");

        switch ( view.getId() ) {

            case R.id.zeroBtn:

                if ( notEquals ) {

                    if ( !bText.endsWith("%") ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + "0");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + "0)");
                        }
                    }

                } else {

                    bigEditText.setText("0");
                }

                if ( sLength > 0 && sText.endsWith("=") ) {

                    smallEditText.setText("");
                }
                break;

            case R.id.oneBtn:

                if ( notEquals ) {

                    if ( !bText.endsWith("%") ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + "1");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + "1)");
                        }
                    }

                } else {

                    bigEditText.setText("1");
                }

                if ( sLength > 0 && sText.endsWith("=") ) {

                    smallEditText.setText("");
                }
                break;

            case R.id.twoBtn:

                if ( notEquals ) {

                    if ( !bText.endsWith("%") ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + "2");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + "2)");
                        }
                    }

                } else {

                    bigEditText.setText("2");
                }

                if ( sLength > 0 && sText.endsWith("=") ) {

                    smallEditText.setText("");
                }
                break;

            case R.id.threeBtn:

                if ( notEquals ) {

                    if ( !bText.endsWith("%") ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + "3");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + "3)");
                        }
                    }

                } else {

                    bigEditText.setText("3");
                }

                if ( sLength > 0 && sText.endsWith("=") ) {

                    smallEditText.setText("");
                }
                break;

            case R.id.fourBtn:

                if ( notEquals ) {

                    if ( !bText.endsWith("%") ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + "4");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + "4)");
                        }
                    }

                } else {

                    bigEditText.setText("4");
                }

                if ( sLength > 0 && sText.endsWith("=") ) {

                    smallEditText.setText("");
                }
                break;

            case R.id.fiveBtn:

                if ( notEquals ) {

                    if ( !bText.endsWith("%") ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + "5");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + "5)");
                        }
                    }

                } else {

                    bigEditText.setText("5");
                }

                if ( sLength > 0 && sText.endsWith("=") ) {

                    smallEditText.setText("");
                }
                break;

            case R.id.sixBtn:

                if ( notEquals ) {

                    if ( !bText.endsWith("%") ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + "6");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + "6)");
                        }
                    }

                } else {

                    bigEditText.setText("6");
                }

                if ( sLength > 0 && sText.endsWith("=") ) {

                    smallEditText.setText("");
                }
                break;

            case R.id.sevenBtn:

                if ( notEquals ) {

                    if ( !bText.endsWith("%") ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + "7");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + "7)");
                        }
                    }

                } else {

                    bigEditText.setText("7");
                }

                if ( sLength > 0 && sText.endsWith("=") ) {

                    smallEditText.setText("");
                }
                break;

            case R.id.eightBtn:

                if ( notEquals ) {

                    if ( !bText.endsWith("%") ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + "8");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + "8)");
                        }
                    }

                } else {

                    bigEditText.setText("8");
                }

                if ( sLength > 0 && sText.endsWith("=") ) {

                    smallEditText.setText("");
                }
                break;

            case R.id.nineBtn:

                if ( notEquals ) {

                    if ( !bText.endsWith("%") ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + "9");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + "9)");
                        }
                    }

                } else {

                    bigEditText.setText("9");
                }

                if ( sLength > 0 && sText.endsWith("=") ) {

                    smallEditText.setText("");
                }
                break;

            case R.id.commaBtn:

                if ( !bText.contains(",") && !bText.endsWith("%") ) {

                    if ( !bText.equals(divideError) ) {

                        if ( !bText.endsWith(")") ) {

                            bigEditText.setText(null);
                            bigEditText.append(bText + ",");

                        } else {

                            bigEditText.setText(null);
                            bigEditText.append(bText.subSequence(0, bLength - 1) + ",)");
                        }
                    }

                    if ( sLength > 0 && sText.subSequence(sLength-1, sLength).equals("=") ) {

                        smallEditText.setText("");
                    }
                }
                break;

            case R.id.signBtn:

                if ( bLength > 0 ) {

                    if ( !bText.equals("0") && !bText.equals("0,") && !bText.equals(",") ) {

                        String subSequence = (String) bText.subSequence(bText.indexOf(",") + 1, bLength);

                        if ( bText.startsWith("0") && onlyZeros(subSequence) ) { break; }
                        if ( bText.startsWith(",") && onlyZeros(subSequence) ) { break; }

                        String before = bText;
                        if ( bText.contains("-") ) { before = (String) bText.subSequence(1, bLength - 1); }

                        before = before.replace(",", ".");

                        boolean percent = false;
                        String percents = "";
                        if ( before.contains("%") ) {

                            percents = (String) before.subSequence(before.indexOf("%"), before.length());
                            int length = percents.length();

                            before = (String) before.subSequence(0, before.indexOf("%"));
                            percent = true;
                        }

                        double x = Double.parseDouble(before);
                        double y = -1;
                        if ( bText.contains("-") ) { y = 1; }

                        String after = String.valueOf(Math.copySign(x, y)).replace(".", ",");

                        if ( onlyZeros((String) after.subSequence(after.indexOf(",") + 1, after.length())) ) {

                            after = (String) after.subSequence(0, after.indexOf(","));
                        }

                        if ( percent ) { after += percents; }

                        String print = "(" + after + ")";
                        if ( bText.contains("-") ) { print = after; }

                        bigEditText.setText(null);
                        bigEditText.append(print);
                    }
                }
                break;
        }
    }
}
