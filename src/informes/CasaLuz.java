package informes;

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
        deben ser calculados de la siguiente forma: Consulta (cancela por kWh),
        Aura ((monto de consumo - porcentaje consulta) * 25%) y Casa (monto de
        consumo - porcentaje consulta - porcentaje aura).

        MONTO DE CUENTA: Es el monto total que se debe de cancelar que, equivale
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

        5490 = 1647 + 1372.5 + 2470.5
    */

    public static void main(String[] args) {

        int montoCancelado

    }

}
