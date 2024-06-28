package Model;

import java.text.DecimalFormat;

public class Utils {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static String formatarValor(double valor) {
        return df.format(valor);
    }
}
