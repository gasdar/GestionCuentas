package informes;

import helpers.ReportFormats;

import javax.swing.*;

public class CasaLuz {

    /*
        OBTENER RÁPIDAMENTE LOS MONTOS Y PORCENTAJES, PARA CANCELAR LA LUZ
        DE LA CASA DE LO ARCAYA

        Tener en cuenta que existen 2 montos que se suman para establecer
        el porcentaje divido en 3 entidades (Consulta, Aura, Casa). A
        continuación veremos cómo se deberían obtener los montos y dividirlos
        entre las entidades:

        MONTO COMPARTIDO: Son servicios que se están relacionados con la
        cuenta de luz, pero que no forman parte del consumo eléctrico y
        que estos deben ser pagados por los siguientes porcentajes:
        Consulta 45%, Aura 25%, Casa 30%.

        MONTO DE CONSUMO: Es el monto de consumo de electricidad de la cuenta y
        deben ser calculados de la siguiente forma: Consulta (cancela por kWh,
        kWhValor * kWhConsumidos), Aura ((monto de consumo - porcentaje consulta) * 25%)
        y Casa (monto de consumo - porcentaje consulta - porcentaje aura).

        MONTO DE CUENTA: Es el monto total que se debe de cancelar y este equivale
        a: monto compartido + monto de consumo.

        Otras consideraciones y cálculos a tener en cuenta para generar el
        informe de la cuenta de luz:

        1. MONTO COMPARTIDO = MONTO DE CUENTA - MONTO DE CONSUMO.
        2. Valor kWh = MONTO DE CONSUMO / Total de kWh consumidos.
        3. Consumo kWh Consulta = kWh consumidos * valor kWh.
        4. Total Consulta = porcentaje compartido + porcentaje de consumo.
        5. Total Aura = porcentaje compartido + porcentaje de consumo.
        6. Total Casa = porcentaje compartido + porcentaje de consumo.
        7. MONTO DE CUENTA = Total Consulta + Total Aura + Total Casa.

        Ejemplo de obtención del MONTO COMPARTIDO:
        5490 = 1647 + 1372.5 + 2470.5

    */

    public static void main(String[] args) {

        int montoCancelado, montoCuenta, montoConsumo, montoCompartido, kwhTotalConsumidos;
        int kwhConsultaConsumidos, kwhValor;
        StringBuilder sb = new StringBuilder();
        String resultado;

        // ERROR ALGORÍTMICO
        ReportFormats.informationMessage("EJEMPLO", "(ReportFormats.reportNumberFormat(1200000)) = " + (ReportFormats.reportNumberFormat(1200000)), 2);
        ReportFormats.informationMessage("EJEMPLO", "(ReportFormats.reportNumberFormat(12000)) = " + (ReportFormats.reportNumberFormat(12000)), 2);
        ReportFormats.informationMessage("EJEMPLO", "(ReportFormats.reportNumberFormat(1200)) = " + (ReportFormats.reportNumberFormat(1200)), 2);

        ReportFormats.reportAbout("LUZ");
        montoCancelado = ReportFormats.getIntUpperThanZero("Ingrese el último monto cancelado");
        montoCuenta = ReportFormats.getIntUpperThanZero("Ingrese el monto actual");
        montoConsumo = ReportFormats.getIntUpperThanZero("Ingrese el monto de electricidad consumida");
        kwhTotalConsumidos = ReportFormats.getIntUpperThanZero("Ingrese el monto de kWh consumidos");

        // Calcular porcentajes de MONTO COMPARTIDO
        int compartidoConsulta, compartidoAura, compartidoCasa, compartidoTotal;

        montoCompartido = montoCuenta - montoConsumo;
        compartidoConsulta = ReportFormats.roundedDecimalFormat(montoCompartido * 0.45f);
        compartidoAura = ReportFormats.roundedDecimalFormat(montoCompartido * 0.25f);
        compartidoCasa = ReportFormats.roundedDecimalFormat(montoCompartido * 0.3f);
        compartidoTotal = compartidoConsulta + compartidoAura + compartidoCasa;

        // Calcular porcentajes de MONTO DE CONSUMO
        int consumoConsulta, consumoAura, consumoCasa, consumoTotal;
        // Obtener kWh consulta
        kwhConsultaConsumidos = consultationKwh();
        kwhValor = ReportFormats.roundedDecimalFormat((float) montoConsumo / kwhTotalConsumidos);

        consumoConsulta = kwhConsultaConsumidos * kwhValor;
        consumoAura = ReportFormats.roundedDecimalFormat((montoConsumo - consumoConsulta) * 0.25f);
        consumoCasa = montoConsumo - consumoConsulta - consumoAura;
        consumoTotal = consumoConsulta+consumoAura+consumoCasa;

        // Calcular los totales finales
        int totalConsulta, totalAura, totalCasa, totalFinal;
        totalConsulta = compartidoConsulta + consumoConsulta;
        totalAura = compartidoAura + consumoAura;
        totalCasa = compartidoCasa + consumoCasa;
        totalFinal = totalConsulta+totalAura+totalCasa;

        sb.append("Último Monto de Cuenta: ").append(ReportFormats.reportNumberFormat(montoCancelado));
        // MIS PRUEBAS UNITARIAS DE LA IGUALDAD DE LOS MONTOS
        sb.append("\n--------------------------------------------------");/*
        sb.append("\n(montoCompartido == compartidoTotal) = ").append(montoCompartido == compartidoTotal);
        sb.append("\n(montoConsumo == consumoTotal) = ").append(montoConsumo == consumoTotal);
        sb.append("\n(montoCuenta == totalFinal) = ").append(montoCuenta == totalFinal);
        sb.append("\n--------------------------------------------------");*/
        sb.append("\nActual Monto de Cuenta: ").append(ReportFormats.reportNumberFormat(montoCuenta));
        sb.append("\nMonto de Consumo: ").append(ReportFormats.reportNumberFormat(montoConsumo));
        sb.append("\nMonto Compartido: ").append(ReportFormats.reportNumberFormat(montoCompartido));
        sb.append("\nkWh Consumidos: ").append(ReportFormats.reportNumberFormat(kwhTotalConsumidos));
        sb.append("\nkWh Valor: ").append(ReportFormats.reportNumberFormat(kwhValor));
        sb.append("\n--------------------------------------------------");
        sb.append("\nConsulta (Consumo & Compartido): ").append(ReportFormats.reportNumberFormat(consumoConsulta)).append(" + ");
        sb.append(ReportFormats.reportNumberFormat(compartidoConsulta)).append(" => ").append(ReportFormats.reportNumberFormat(totalConsulta));
        sb.append("\nAura (Consumo & Compartido): ").append(ReportFormats.reportNumberFormat(consumoAura)).append(" + ");
        sb.append(ReportFormats.reportNumberFormat(compartidoAura)).append(" => ").append(ReportFormats.reportNumberFormat(totalAura));
        sb.append("\nCasa (Consumo & Compartido): ").append(ReportFormats.reportNumberFormat(consumoCasa)).append(" + ");
        sb.append(ReportFormats.reportNumberFormat(compartidoCasa)).append(" => ").append(ReportFormats.reportNumberFormat(totalCasa));
        sb.append("\n--------------------------------------------------");
        sb.append("\nTotal Final (Consulta & Aura & Casa): ").append(ReportFormats.reportNumberFormat(totalConsulta)).append(" + ");
        sb.append(ReportFormats.reportNumberFormat(totalAura)).append(" + ").append(ReportFormats.reportNumberFormat(totalCasa));
        sb.append(" => ").append(ReportFormats.reportNumberFormat(totalFinal));

        resultado = sb.toString();
        JOptionPane.showMessageDialog(null,
                resultado,
                "INFORME DE CUENTA DE LUZ",
                JOptionPane.INFORMATION_MESSAGE);

    }

    private static int consultationKwh() {
        ReportFormats.informationMessage("CONSULTA KWH", "Debe ingresar los kWh consumidos de la consulta dental", 1);
        int ultimaMarcaKwh, actualMarcaKwh;
        do {
            ultimaMarcaKwh = ReportFormats.getIntUpperThanZero("Ingrese la última marca de kWh de la consulta");
            actualMarcaKwh = ReportFormats.getIntUpperThanZero("Ingrese la actual marca de kWh de la consulta");
            if (ultimaMarcaKwh > actualMarcaKwh) {
                ReportFormats.informationMessage("KWH INGRESADOS INVÁLIDOS", "La última marca de kWh, no puede ser mayor a la actual", 2);
            }
        } while (ultimaMarcaKwh > actualMarcaKwh);
        return actualMarcaKwh - ultimaMarcaKwh;
    }

}
