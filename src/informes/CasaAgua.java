package informes;

import helpers.ReportFormats;

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
        ReportFormats.informReportProcess("AGUA");

        montoCancelado = ReportFormats.getIntUpperThanZero("Ingrese el último monto cancelado");
        montoActual = ReportFormats.getIntUpperThanZero("Ingrese el monto actual a cancelar");
        cuotaCuenta = ReportFormats.roundFormat(montoActual / 7.0f);

        sb.append("Último monto cancelado => ").append(ReportFormats.reportNumberFormat(montoCancelado));
        sb.append("\nMonto actual a cancelar => ").append(ReportFormats.reportNumberFormat(montoActual));
        sb.append("\nCuota divida en 7 => ").append(ReportFormats.reportNumberFormat(cuotaCuenta));

        resultado = sb.toString();

        JOptionPane.showMessageDialog(null,
                resultado,
                "INFORME DE CUENTA DE AGUA",
                JOptionPane.INFORMATION_MESSAGE);

    }

}
