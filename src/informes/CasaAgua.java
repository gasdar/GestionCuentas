package informes;

import javax.swing.*;

public class CasaAgua {

    /*
        OBTENER LA INFORMACIÓN DE LA CUENTA DEL AGUA ACTUAL:

        Se espera conocer: El último monto cancelado y el monto actual
        a cancelar, divida la cuota por 7 partes iguales.
    */

    public static void main(String[] args) {

        int montoCancelado, montoActual, cuotaCuenta;
        StringBuilder sb = new StringBuilder();
        String resultado;
        JOptionPane.showMessageDialog(null,
                "Ingrese los datos solicitados para obtener\nel informe del agua.",
                "INFORME DE CUENTA DE AGUA",
                JOptionPane.INFORMATION_MESSAGE);

        montoCancelado = getIntUpperThanZero("Ingrese el último monto cancelado");
        montoActual = getIntUpperThanZero("Ingrese el monto actual a cancelar");
        cuotaCuenta = roundFormat(montoActual / 7.0f);

        sb.append("Último monto cancelado => ").append(reportNumberFormat(montoCancelado));
        sb.append("\nMonto actual a cancelar => ").append(reportNumberFormat(montoActual));
        sb.append("\nCuota divida en 7 => ").append(reportNumberFormat(cuotaCuenta));

        resultado = sb.toString();

        JOptionPane.showMessageDialog(null,
                resultado,
                "INFORME DE CUENTA DE AGUA",
                JOptionPane.INFORMATION_MESSAGE);

    }

    private static int getIntUpperThanZero(String m) {
        int num;
        try {
            do {
                num = Integer.parseInt(JOptionPane.showInputDialog(null, m));
                if(num <= 0) {
                    JOptionPane.showMessageDialog(null,
                            "Debe ingresar un número mayor a 0",
                            "INGRESAR NÚMERO MAYOR",
                            JOptionPane.WARNING_MESSAGE);
                }
            } while (num <= 0);
            return num;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Debe ingresar un número entero",
                    "ERROR FORMATO",
                    JOptionPane.ERROR_MESSAGE);
            return getIntUpperThanZero(m);
        }
    }

    private static int roundFormat(float num) {
        // redondeamos el decimal y volvemos el puntero
        float decimalValue = roundFormatHelper(num * 10) / 10;
        // redondeamos el último número
        return (int) roundFormatHelper(decimalValue);
    }

    private static float roundFormatHelper(float num) {
        String sNum = Float.toString(num); // 2434.0 => a String
        sNum = sNum.substring(0, sNum.indexOf('.')); // "2434.0" => "2434"
        int finalNum = Integer.parseInt(sNum.substring(sNum.length()-1)); // "2434" => 4
        if(finalNum == 0) {
            return num;
        }
        return (finalNum >= 5) ? num + ((finalNum - 10) * -1) : num - finalNum;
    }

    private static String reportNumberFormat(int num) {
        int counter=0;
        String sNum = Integer.toString(num);
        StringBuilder sb = new StringBuilder();
        for(int i=sNum.length()-1; i>=0; i--) {
            counter++;
            if(counter == 3) {
                sb.append(sNum.charAt(i)).append(".");
                counter = 0;
            } else {
                sb.append(sNum.charAt(i));
            }
        }
        // Número con formato al revés y reseteamos el String Builder
        sNum = sb.toString();
        sb.delete(0, sb.toString().length());
        for(int i=sNum.length()-1; i>=0; i--) {
            sb.append(sNum.charAt(i));
        }
        return sb.toString();
    }

}
