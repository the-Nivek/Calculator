package de.indie_development.calculator.listener.buttons;

import android.view.View;
import android.view.View.OnClickListener;

import de.indie_development.calculator.R;

import static de.indie_development.calculator.activities.MainActivity.vibrator;
import static de.indie_development.calculator.util.Methods.calculateWithPercents;
import static de.indie_development.calculator.util.Methods.onlyZeros;
import static de.indie_development.calculator.util.Methods.setDigits;
import static de.indie_development.calculator.util.Values.bigEditText;
import static de.indie_development.calculator.util.Values.divideError;
import static de.indie_development.calculator.util.Values.smallEditText;
import static de.indie_development.calculator.util.Values.sp;

public class OperatorButtons implements OnClickListener {

    @Override
    public void onClick(View view) {

        if ( sp.getString("keyVibration", "false").equals("true") ) { vibrator.vibrate(50); }

        String bText = (String) bigEditText.getText().toString();
        String sText = (String) smallEditText.getText().toString();

        int bLength = bText.length();
        int sLength = sText.length();

        switch ( view.getId() ) {

            case R.id.addBtn:

                if ( !sText.endsWith("=") ) {

                    if ( bLength > 0 ) {

                        if ( !bText.equals(divideError) ) {

                            if ( bText.contains(",") ) {

                                if ( bText.startsWith(",") ) {

                                    if ( !onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                        String output = "0" + bText;

                                        if ( bText.contains("%") ) {

                                            String percents = (String) bText.subSequence(bText.indexOf("%"), bLength);
                                            if ( bText.contains("-") ) { percents = (String) percents.subSequence(0, percents.length() - 1); }
                                            int length = percents.length();

                                            if ( bText.contains("(-") ) {

                                                double number = Double.parseDouble(bigEditText.getText().subSequence(2, bigEditText.getText().toString().indexOf("%")).toString());
                                                output = calculateWithPercents(percents, length, number);

                                                double x = Double.parseDouble(output.replace(",", "."));
                                                double y = -1;
                                                double n = Math.copySign(x, y);

                                                output = "(" + setDigits(String.valueOf(n)).replace(".", ",") + ")";

                                            } else {

                                                double number = Double.parseDouble(bigEditText.getText().subSequence(0, bigEditText.getText().toString().indexOf("%")).toString());
                                                output = calculateWithPercents(percents, length, number);
                                            }
                                        }

                                        smallEditText.setText(null);
                                        smallEditText.append(sText + output + " + ");
                                        bigEditText.setText("");

                                    } else {

                                        smallEditText.setText(null);
                                        smallEditText.append(sText + "0" + " + ");
                                        bigEditText.setText("");
                                    }

                                } else if ( bText.endsWith(",") ) {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "" + bText.subSequence(0, bLength-1) + " + ");
                                    bigEditText.setText("");

                                } else if ( onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "" + bText.subSequence(0, bText.indexOf(",")) + " + ");
                                    bigEditText.setText("");

                                } else {

                                    String output = bText;

                                    if ( bText.contains("%") ) {

                                        String percents = (String) bText.subSequence(bText.indexOf("%"), bLength);
                                        if ( bText.contains("-") ) { percents = (String) percents.subSequence(0, percents.length() - 1); }
                                        int length = percents.length();

                                        if ( bText.contains("(-") ) {

                                            double number = Double.parseDouble(bigEditText.getText().subSequence(2, bigEditText.getText().toString().indexOf("%")).toString());
                                            output = calculateWithPercents(percents, length, number);

                                            double x = Double.parseDouble(output.replace(",", "."));
                                            double y = -1;
                                            double n = Math.copySign(x, y);

                                            output = "(" + setDigits(String.valueOf(n)).replace(".", ",") + ")";

                                        } else {

                                            double number = Double.parseDouble(bigEditText.getText().subSequence(0, bigEditText.getText().toString().indexOf("%")).toString());
                                            output = calculateWithPercents(percents, length, number);
                                        }
                                    }

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + output + " + ");
                                    bigEditText.setText("");
                                }

                            } else {

                                String output = bText;

                                if ( bText.contains("%") ) {

                                    String percents = (String) bText.subSequence(bText.indexOf("%"), bLength);
                                    if ( bText.contains("-") ) { percents = (String) percents.subSequence(0, percents.length() - 1); }
                                    int length = percents.length();

                                    if ( bText.contains("(-") ) {

                                        double number = Double.parseDouble(bigEditText.getText().subSequence(2, bigEditText.getText().toString().indexOf("%")).toString());
                                        output = calculateWithPercents(percents, length, number);

                                        double x = Double.parseDouble(output.replace(",", "."));
                                        double y = -1;
                                        double n = Math.copySign(x, y);

                                        output = "(" + setDigits(String.valueOf(n)).replace(".", ",") + ")";

                                    } else {

                                        double number = Double.parseDouble(bigEditText.getText().subSequence(0, bigEditText.getText().toString().indexOf("%")).toString());
                                        output = calculateWithPercents(percents, length, number);
                                    }
                                }

                                smallEditText.setText(null);
                                smallEditText.append(sText + output + " + ");
                                bigEditText.setText("");
                            }
                        }

                    } else if ( sLength > 0 ) {

                        if ( sText.subSequence(sLength-2, sLength).equals("- ") || sText.subSequence(sLength-2, sLength).equals("× ") || sText.subSequence(sLength-2, sLength).equals("÷ ") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText.subSequence(0, sLength-3) + " + ");

                        } else if ( sText.subSequence(sLength-4, sLength).equals("mod ") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText.subSequence(0, sLength-5) + " + ");
                        }
                    }

                } else {

                    if ( !bText.equals(divideError) ) {

                        smallEditText.setText(null);
                        smallEditText.append(bText + " + ");
                        bigEditText.setText("");
                    }
                }
                break;

            case R.id.subtractBtn:

                if ( !sText.endsWith("=") ) {

                    if ( bLength > 0 ) {

                        if ( !bText.equals(divideError) ) {

                            if ( bText.contains(",") ) {

                                if ( bText.startsWith(",") ) {

                                    if ( !onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                        String output = "0" + bText;

                                        if ( bText.contains("%") ) {

                                            String percents = (String) bText.subSequence(bText.indexOf("%"), bLength);
                                            if ( bText.contains("-") ) { percents = (String) percents.subSequence(0, percents.length() - 1); }
                                            int length = percents.length();

                                            if ( bText.contains("(-") ) {

                                                double number = Double.parseDouble(bigEditText.getText().subSequence(2, bigEditText.getText().toString().indexOf("%")).toString());
                                                output = calculateWithPercents(percents, length, number);

                                                double x = Double.parseDouble(output.replace(",", "."));
                                                double y = -1;
                                                double n = Math.copySign(x, y);

                                                output = "(" + setDigits(String.valueOf(n)).replace(".", ",") + ")";

                                            } else {

                                                double number = Double.parseDouble(bigEditText.getText().subSequence(0, bigEditText.getText().toString().indexOf("%")).toString());
                                                output = calculateWithPercents(percents, length, number);
                                            }
                                        }

                                        smallEditText.setText(null);
                                        smallEditText.append(sText + output + " - ");
                                        bigEditText.setText("");

                                    } else {

                                        smallEditText.setText(null);
                                        smallEditText.append(sText + "0" + " - ");
                                        bigEditText.setText("");
                                    }

                                } else if ( bText.endsWith(",") ) {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "" + bText.subSequence(0, bLength-1) + " - ");
                                    bigEditText.setText("");

                                } else if ( onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "" + bText.subSequence(0, bText.indexOf(",")) + " - ");
                                    bigEditText.setText("");

                                } else {

                                    String output = bText;

                                    if ( bText.contains("%") ) {

                                        String percents = (String) bText.subSequence(bText.indexOf("%"), bLength);
                                        if ( bText.contains("-") ) { percents = (String) percents.subSequence(0, percents.length() - 1); }
                                        int length = percents.length();

                                        if ( bText.contains("(-") ) {

                                            double number = Double.parseDouble(bigEditText.getText().subSequence(2, bigEditText.getText().toString().indexOf("%")).toString());
                                            output = calculateWithPercents(percents, length, number);

                                            double x = Double.parseDouble(output.replace(",", "."));
                                            double y = -1;
                                            double n = Math.copySign(x, y);

                                            output = "(" + setDigits(String.valueOf(n)).replace(".", ",") + ")";

                                        } else {

                                            double number = Double.parseDouble(bigEditText.getText().subSequence(0, bigEditText.getText().toString().indexOf("%")).toString());
                                            output = calculateWithPercents(percents, length, number);
                                        }
                                    }

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + output + " - ");
                                    bigEditText.setText("");
                                }

                            } else {

                                String output = bText;

                                if ( bText.contains("%") ) {

                                    String percents = (String) bText.subSequence(bText.indexOf("%"), bLength);
                                    if ( bText.contains("-") ) { percents = (String) percents.subSequence(0, percents.length() - 1); }
                                    int length = percents.length();

                                    if ( bText.contains("(-") ) {

                                        double number = Double.parseDouble(bigEditText.getText().subSequence(2, bigEditText.getText().toString().indexOf("%")).toString());
                                        output = calculateWithPercents(percents, length, number);

                                        double x = Double.parseDouble(output.replace(",", "."));
                                        double y = -1;
                                        double n = Math.copySign(x, y);

                                        output = "(" + setDigits(String.valueOf(n)).replace(".", ",") + ")";

                                    } else {

                                        double number = Double.parseDouble(bigEditText.getText().subSequence(0, bigEditText.getText().toString().indexOf("%")).toString());
                                        output = calculateWithPercents(percents, length, number);
                                    }
                                }

                                smallEditText.setText(null);
                                smallEditText.append(sText + output + " - ");
                                bigEditText.setText("");
                            }
                        }

                    } else if ( sLength > 0 ) {

                        if ( sText.subSequence(sLength-2, sLength).equals("- ") || sText.subSequence(sLength-2, sLength).equals("× ") || sText.subSequence(sLength-2, sLength).equals("÷ ") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText.subSequence(0, sLength-3) + " - ");

                        } else if ( sText.subSequence(sLength-4, sLength).equals("mod ") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText.subSequence(0, sLength-5) + " - ");
                        }
                    }

                } else {

                    if ( !bText.equals(divideError) ) {

                        smallEditText.setText(null);
                        smallEditText.append(bText + " - ");
                        bigEditText.setText("");
                    }
                }
                break;

            case R.id.multiplyBtn:

                if ( !sText.endsWith("=") ) {

                    if ( bLength > 0 ) {

                        if ( !bText.equals(divideError) ) {

                            if ( bText.contains(",") ) {

                                if ( bText.startsWith(",") ) {

                                    if ( !onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                        String output = "0" + bText;

                                        if ( bText.contains("%") ) {

                                            String percents = (String) bText.subSequence(bText.indexOf("%"), bLength);
                                            if ( bText.contains("-") ) { percents = (String) percents.subSequence(0, percents.length() - 1); }
                                            int length = percents.length();

                                            if ( bText.contains("(-") ) {

                                                double number = Double.parseDouble(bigEditText.getText().subSequence(2, bigEditText.getText().toString().indexOf("%")).toString());
                                                output = calculateWithPercents(percents, length, number);

                                                double x = Double.parseDouble(output.replace(",", "."));
                                                double y = -1;
                                                double n = Math.copySign(x, y);

                                                output = "(" + setDigits(String.valueOf(n)).replace(".", ",") + ")";

                                            } else {

                                                double number = Double.parseDouble(bigEditText.getText().subSequence(0, bigEditText.getText().toString().indexOf("%")).toString());
                                                output = calculateWithPercents(percents, length, number);
                                            }
                                        }

                                        smallEditText.setText(null);
                                        smallEditText.append(sText + output + " × ");
                                        bigEditText.setText("");

                                    } else {

                                        smallEditText.setText(null);
                                        smallEditText.append(sText + "0" + " × ");
                                        bigEditText.setText("");
                                    }

                                } else if ( bText.endsWith(",") ) {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "" + bText.subSequence(0, bLength-1) + " × ");
                                    bigEditText.setText("");

                                } else if ( onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "" + bText.subSequence(0, bText.indexOf(",")) + " × ");
                                    bigEditText.setText("");

                                } else {

                                    String output = bText;

                                    if ( bText.contains("%") ) {

                                        String percents = (String) bText.subSequence(bText.indexOf("%"), bLength);
                                        if ( bText.contains("-") ) { percents = (String) percents.subSequence(0, percents.length() - 1); }
                                        int length = percents.length();

                                        if ( bText.contains("(-") ) {

                                            double number = Double.parseDouble(bigEditText.getText().subSequence(2, bigEditText.getText().toString().indexOf("%")).toString());
                                            output = calculateWithPercents(percents, length, number);

                                            double x = Double.parseDouble(output.replace(",", "."));
                                            double y = -1;
                                            double n = Math.copySign(x, y);

                                            output = "(" + setDigits(String.valueOf(n)).replace(".", ",") + ")";

                                        } else {

                                            double number = Double.parseDouble(bigEditText.getText().subSequence(0, bigEditText.getText().toString().indexOf("%")).toString());
                                            output = calculateWithPercents(percents, length, number);
                                        }
                                    }

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + output + " × ");
                                    bigEditText.setText("");
                                }

                            } else {

                                String output = bText;

                                if ( bText.contains("%") ) {

                                    String percents = (String) bText.subSequence(bText.indexOf("%"), bLength);
                                    if ( bText.contains("-") ) { percents = (String) percents.subSequence(0, percents.length() - 1); }
                                    int length = percents.length();

                                    if ( bText.contains("(-") ) {

                                        double number = Double.parseDouble(bigEditText.getText().subSequence(2, bigEditText.getText().toString().indexOf("%")).toString());
                                        output = calculateWithPercents(percents, length, number);

                                        double x = Double.parseDouble(output.replace(",", "."));
                                        double y = -1;
                                        double n = Math.copySign(x, y);

                                        output = "(" + setDigits(String.valueOf(n)).replace(".", ",") + ")";

                                    } else {

                                        double number = Double.parseDouble(bigEditText.getText().subSequence(0, bigEditText.getText().toString().indexOf("%")).toString());
                                        output = calculateWithPercents(percents, length, number);
                                    }
                                }

                                smallEditText.setText(null);
                                smallEditText.append(sText + output + " × ");
                                bigEditText.setText("");
                            }
                        }

                    } else if ( sLength > 0 ) {

                        if ( sText.subSequence(sLength-2, sLength).equals("- ") || sText.subSequence(sLength-2, sLength).equals("× ") || sText.subSequence(sLength-2, sLength).equals("÷ ") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText.subSequence(0, sLength-3) + " × ");

                        } else if ( sText.subSequence(sLength-4, sLength).equals("mod ") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText.subSequence(0, sLength-5) + " × ");
                        }
                    }

                } else {

                    if ( !bText.equals(divideError) ) {

                        smallEditText.setText(null);
                        smallEditText.append(bText + " × ");
                        bigEditText.setText("");
                    }
                }
                break;

                // ersetzen mit multiply code und durch zeichen einsetzen; auch bei modulo

            case R.id.divideBtn:

                if ( !sText.endsWith("=") ) {

                    if ( bLength > 0 ) {

                        if ( !bText.equals(divideError) ) {

                            if ( bText.startsWith(",") ) {

                                if ( !onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "0" + bText + " ÷ ");
                                    bigEditText.setText("");

                                } else {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "0" + " ÷ ");
                                    bigEditText.setText("");
                                }

                            } else if ( bText.endsWith(",") ) {

                                smallEditText.setText(null);
                                smallEditText.append(sText + bText.subSequence(0, bLength-1) + " ÷ ");
                                bigEditText.setText("");

                            } else {

                                if ( bText.contains(",") ) {

                                    if ( onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                        smallEditText.setText(null);
                                        smallEditText.append(sText + "" + bText.subSequence(0, bText.indexOf(",")) + " ÷ ");
                                        bigEditText.setText("");

                                    } else {

                                        smallEditText.setText(null);
                                        smallEditText.append(sText + "" + bText + " ÷ ");
                                        bigEditText.setText("");
                                    }

                                } else {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "" + bText + " ÷ ");
                                    bigEditText.setText("");
                                }
                            }
                        }

                    } else if ( sLength > 0 ) {

                        if ( sText.subSequence(sLength-2, sLength).equals("+ ") || sText.subSequence(sLength-2, sLength).equals("- ") || sText.subSequence(sLength-2, sLength).equals("× ") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText.subSequence(0, sLength-3) + " ÷ ");

                        } else if ( sText.subSequence(sLength-4, sLength).equals("mod ") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText.subSequence(0, sLength-5) + " ÷ ");
                        }
                    }

                } else {

                    if ( !bText.equals(divideError) ) {

                        smallEditText.setText(null);
                        smallEditText.append(bText + " ÷ ");
                        bigEditText.setText("");
                    }
                }
                break;

            case R.id.moduloBtn:

                if ( !sText.endsWith("=") ) {

                    if ( bLength > 0 ) {

                        if ( !bText.equals(divideError) ) {

                            if ( bText.startsWith(",") ) {

                                if ( !onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "0" + bText + " mod ");
                                    bigEditText.setText("");

                                } else {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "0" + " mod ");
                                    bigEditText.setText("");
                                }

                            } else if ( bText.endsWith(",") ) {

                                smallEditText.setText(null);
                                smallEditText.append(sText + "" + bText.subSequence(0, bLength-1) + " mod ");
                                bigEditText.setText("");

                            } else {

                                if ( bText.contains(",") ) {

                                    if ( onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                        smallEditText.setText(null);
                                        smallEditText.append(sText + "" + bText.subSequence(0, bText.indexOf(",")) + " mod ");
                                        bigEditText.setText("");

                                    } else {

                                        smallEditText.setText(null);
                                        smallEditText.append(sText + "" + bText + " mod ");
                                        bigEditText.setText("");
                                    }

                                } else {

                                    smallEditText.setText(null);
                                    smallEditText.append(sText + "" + bText + " mod ");
                                    bigEditText.setText("");
                                }
                            }
                        }

                    } else if ( sLength > 0 ) {

                        if ( sText.subSequence(sLength-2, sLength).equals("+ ") || sText.subSequence(sLength-2, sLength).equals("- ") || sText.subSequence(sLength-2, sLength).equals("× ") || sText.subSequence(sLength-2, sLength).equals("÷ ") ) {

                            smallEditText.setText(null);
                            smallEditText.append(sText.subSequence(0, sLength-3) + " mod ");
                        }
                    }

                } else {

                    if ( !bText.equals(divideError) ) {

                        smallEditText.setText(null);
                        smallEditText.append(bText + " mod ");
                        bigEditText.setText("");
                    }
                }
                break;

            case R.id.powerBtn:

                if ( !sText.endsWith("=") ) {

                    if ( bLength > 0 ) {



                    }

                }
                break;

            case R.id.rootBtn:
                break;

            case R.id.percentBtn:

                if ( bLength > 0 ) {

                    if ( !bText.equals(divideError) ) {

                        if ( !bText.equals(",") && !bText.equals("0") && !bText.equals("0,") ) {

                            String output = bText;

                            if ( !bText.endsWith(")") ) {

                                if ( onlyZeros((String) bText.subSequence(bText.indexOf(",") + 1, bLength)) ) {

                                    if ( !bText.startsWith("0") && !bText.startsWith(",") ) {

                                        output = (String) bText.subSequence(0, bText.indexOf(",")) + "%";
                                    }

                                } else {

                                    if ( bText.startsWith(",") ) {

                                        output = "0" + output;

                                    } else if ( bText.endsWith(",") ) {

                                        output = (String) output.subSequence(0, output.length() - 1);
                                    }

                                    output += "%";
                                }

                            } else {

                                output = bText.subSequence(0, bLength - 1) + "%)";
                            }

                            bigEditText.setText(null);
                            bigEditText.append(output);
                        }
                    }
                }
                break;

        }
    }
}
