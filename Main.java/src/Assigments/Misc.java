package Assigments;

import java.text.DecimalFormat;

public class Misc {

    static public float roundOff(double input) {
        DecimalFormat df = new DecimalFormat("0.00");
        String st = df.format(input);
        return Float.parseFloat(st);
    }
}
